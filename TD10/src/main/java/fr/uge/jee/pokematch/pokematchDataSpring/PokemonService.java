package fr.uge.jee.pokematch.pokematchDataSpring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import javax.transaction.Transactional;

@Service
public class PokemonService {

    @Autowired
    PokemonServiceWithFailure pokemonServiceWithFailure;

    @Autowired
    PokemonRepository pokemonRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;


    @Transactional
    public void insertOrIncrementPokemon(String name) {
        var pokemonToUpdate=em.find(Pokemon.class,name, LockModeType.PESSIMISTIC_WRITE);
        if(pokemonToUpdate == null){
            pokemonRepository.save(new Pokemon(name, 1L));
        }else{
            pokemonToUpdate.setScore(pokemonToUpdate.getScore()+1);
            pokemonRepository.save(pokemonToUpdate);
        }
    }

    @Transactional
    public Long totalCountVote() {
//        var pokemon = pokemonRepository.findByName(name);
//        return pokemon.getScore();
        return pokemonRepository.countTotalScore();
    }


    @Transactional
    public void incrementScoreWithOptimisticLock(String name){
        var retry=true;
        while(retry) {
            retry=false;
            try {
                pokemonServiceWithFailure.incrementScoreWrong(name);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e){
                retry=true;
            }
        }
    }
}
