package fr.uge.jee.pokematch.pokematchDataSpring;

import org.springframework.stereotype.Service;

@Service
public class PokemonServiceWithFailure {

    @Transactional
    public void incrementScoreWrong(String name){
        var pokemon = pokemonRepository.findByName(name);
        pokemon.incrementScore();
        pokemonRepository.save(pokemon);
    }
}
