package fr.uge.jee.springmvc.pokematch.controller;

import fr.uge.jee.springmvc.pokematch.model.Pokedex;
import fr.uge.jee.springmvc.pokematch.model.PokemonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;



@Component
class PokedexHandler {

    @Autowired
    private Pokedex pokedex;

    private final Object lock = new Object();
    private final LinkedList<PokemonDto> topPokemons = new LinkedList<>();


    void incrementOccurence(PokemonDto pok){
        synchronized (lock){
            var itr = topPokemons.iterator();
            while(itr.hasNext()){
                var currentPok = itr.next();
                if(currentPok.equals(pok)){
                    currentPok.incrementOccurence();
                    return;
                }
            }
            pok.incrementOccurence();
            topPokemons.addFirst(pok);
        }
    }

    List<PokemonDto> topNPokemonsByOccurence(int pokNumber){
        synchronized (lock){
            topPokemons.sort(Comparator.reverseOrder());

            var itr = topPokemons.iterator();
            var topPokemonsList = new ArrayList<PokemonDto>();
            for (int i = 0; i < pokNumber && itr.hasNext(); i++) {
                var pok = itr.next();
                topPokemonsList.add(pok);
            }
            return List.copyOf(topPokemonsList);
        }
    }

    private PokemonDto getPokemon(Integer hashcode){
        synchronized (lock){
           var pok =  pokedex.getPokemon(hashcode);
            incrementOccurence(pok);
            return pok;
        }
    }

     PokemonDto determinePokemonByHashCode(int userHashCode){
         var hashCodesItr = pokedex.pokemonsHashCodes().iterator();
         if(!hashCodesItr.hasNext()){
             return null;
         }
         int current = hashCodesItr.next();
         int diff = Math.abs(current - userHashCode);
         while(hashCodesItr.hasNext()){
             var pokCode = hashCodesItr.next();
             int tmp = Math.abs(pokCode - userHashCode);
             if( tmp < diff){
                 diff =tmp;
                 current = pokCode;
             }
         }
        return getPokemon(current);
     }
}
