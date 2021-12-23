package fr.uge.jee.hibernate.employees;

import fr.uge.jee.hibernate.utility.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class EmployeeRepository {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = PersistenceUtils.getEntityManagerFactory();



    /**
     * Create an employee with the given information
     * @param firstName
     * @param lastName
     * @param salary
     * @return the id of the newly created employee
     */

    public long create(String firstName, String lastName, int salary) {
        assert(salary >= 0);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        return PersistenceUtils.inTransaction(em-> {
            var harry = new Employee(firstName,lastName,salary);
            em.persist(harry);
            return harry.getId();
        });
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
        return PersistenceUtils.inTransaction(em-> {
            var employee = em.find(Employee.class,id);
            if(employee != null){
                em.remove(employee);
            }
            return true;
        });
    }

    /**
     * Update the salary of the employee with the given id
     * @param id
     * @param salary
     * @return true if the removal was successful
     */

    public boolean update(long id, int salary) {
        assert (salary >0);
        return PersistenceUtils.inTransaction(em-> {
            var employee = em.find(Employee.class,id);
            if(employee == null){
                return false;
            }
            employee.setSalary(salary);
            return true;
        });
    }

    public boolean updateSalary(long id, Function<Integer,Integer> function){
        return PersistenceUtils.inTransaction(em-> {
            var employee = em.find(Employee.class,id);
            if(employee == null){
                return false;
            }
            employee.setSalary(function.apply(employee.getSalary()));
            return true;
        });
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Employee> get(long id) {
        return PersistenceUtils.inTransaction(em-> {
            var employee = em.find(Employee.class,id);
            if(employee == null){
                return Optional.empty();
            }
            return Optional.of(employee);
        });
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<Employee> getAll() {
        return PersistenceUtils.inTransaction(em-> {
            TypedQuery<Employee> query = em.createQuery("SELECT s from Employee s",Employee.class);
            List<Employee> results = query.getResultList();
            return results;
        });
    }


    public List<Employee> getAllByFirstName(String firstName){
        return PersistenceUtils.inTransaction(em->{
            var q = "SELECT e FROM Employee e where e.firstName  =:firstname";
            TypedQuery<Employee> query = em.createQuery(q,Employee.class);
            query.setParameter("firstname",firstName);
            return query.getResultList();
        });
    }

}
