package fr.uge.jee.hibernate.students;

import fr.uge.jee.hibernate.employees.Employee;
import fr.uge.jee.hibernate.utility.PersistenceUtils;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public interface Repository<T, K extends Serializable> {

    public Class<T> getClazz();

    default T create(T toCreate){
        return PersistenceUtils.inTransaction(em->{
            em.persist(toCreate);
            return toCreate;
        });
    }

    default boolean delete(K toDeleteId){ // pk on ne peut pas passer par des ids ?
        return PersistenceUtils.inTransaction(em-> {
            var entityToDelete = em.find(getClazz(),toDeleteId);
            if( entityToDelete != null){
                em.remove(entityToDelete);
            }
            return true;
        });
    }

    default boolean update(K idToUpdate, Consumer<T> updateOperationConsumer) {
        Objects.requireNonNull(updateOperationConsumer);
        return PersistenceUtils.inTransaction(em-> {
            var entity = em.find(getClazz(), idToUpdate);
            if(entity == null){
                return false;
            }
            updateOperationConsumer.accept(entity);
            return true;
        });
    }

    default Optional<T> get(K idToGet) {
        return PersistenceUtils.inTransaction(em-> {
            var entity = em.find(getClazz(),idToGet);
            if(entity == null){
                return Optional.empty();
            }
            return Optional.of(entity);
        });
    }

    //public List<T> getAll(); // nécessite PSQL, donc à mettre dans les Repository

}
