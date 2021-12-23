package fr.uge.jee.springmvc.pokematch.model;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Pokedex {

    private static String POKEMON_LIST = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1118";
    private final HashMap<Integer, String> pokemonUrls; // map hashCode du nom de pokemon, url pokemon
    private final HashMap<Integer, PokemonDto> pokemonData; // map hashcode du nom, dto pokemon
    private final WebClient webClient;

    private Pokedex(HashMap<Integer, String> pokemonUrls, HashMap<Integer, PokemonDto> pokemonData, WebClient webClient){
        this.pokemonUrls = pokemonUrls;
        this.pokemonData = pokemonData;
        this.webClient = webClient;
    }

    /**
     * factory method for PokemonBdd
     * @param webClient spring webClient
     * @return PokemonBdd initialized from api
     */
    static Pokedex FromApi(WebClient webClient){
        var pokedexDto = webClient.get()
                .uri(POKEMON_LIST)
                .retrieve()
                .bodyToMono(PokedexDto.class)
                .block();
        var poksInfo = pokedexDto.getPokemonsWithUrl();

        return new Pokedex(poksInfo, new HashMap<>(), webClient);
    }

    public Set<Integer> pokemonsHashCodes(){
        return Set.copyOf(pokemonUrls.keySet());
    }


    public PokemonDto getPokemon(int code){
        var pokemon = pokemonData.get(code);
        if(pokemon == null){
            // get pokemon
            pokemon = webClient.get()
                    .uri(pokemonUrls.get(code))
                    .retrieve()
                    .bodyToMono(PokemonDto.class)
                    .block();
            // load image
            var imageCache = webClient.get()
                    .uri(pokemon.imagePath())
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
            //store image and pokemon in cache
            pokemon.setImageCache(imageCache);
            pokemonData.put(code, pokemon);
        }
        return pokemonData.get(code);
    }
}
