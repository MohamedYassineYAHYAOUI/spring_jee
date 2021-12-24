package fr.uge.jee.bugged.repository;

import fr.uge.jee.bugged.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UserRepository extends CrudRepository<User,Long> {}
