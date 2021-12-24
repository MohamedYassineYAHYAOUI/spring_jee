package fr.uge.jee.pokematch.pokematchDataSpring;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonRepository extends CrudRepository<Pokemon,String> {


    Pokemon findByName(String name);

    @Query("SELECT SUM(p.score) FROM Pokemon p")
    Long countTotalScore();

}
