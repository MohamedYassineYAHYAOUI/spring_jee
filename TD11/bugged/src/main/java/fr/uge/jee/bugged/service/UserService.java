package fr.uge.jee.bugged.service;

import fr.uge.jee.bugged.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<User> searchByLastName(String lastName) {
        var q = "SELECT s FROM User s WHERE s.lastName = '"+lastName+"'";
        return em.createQuery(q,User.class).getResultList();
    }
}
