package fr.uge.jee.hibernate.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

public class PersistenceUtils {

    static final EntityManagerFactory ENTITY_MANAGER_FACTORY
            = Persistence.createEntityManagerFactory("main-persistence-unit");

    public static EntityManagerFactory getEntityManagerFactory(){
        return ENTITY_MANAGER_FACTORY;
    }
    public static void inTransaction(Consumer<EntityManager> consumer){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        var tx = em.getTransaction();
        try{
            tx.begin();
            consumer.accept(em);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    public static <T> T inTransaction(Function<EntityManager, T> function){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        var tx = em.getTransaction();
        T returnVal;
        try{
            tx.begin();
            returnVal = function.apply(em);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            em.close();
        }
        return  returnVal;
    }

}