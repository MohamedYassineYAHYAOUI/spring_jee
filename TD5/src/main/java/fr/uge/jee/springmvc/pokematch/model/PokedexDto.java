package fr.uge.jee.springmvc.pokematch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


public class PokedexDto {
    @JsonProperty("count")
    private int count;
    
    @JsonProperty("results")
    private ResultsDto[] pokemonResults;

    public void setPokemonResults(ResultsDto[] pokemonResults) {
        this.pokemonResults = pokemonResults;
    }
    public void setCount(int count) {
        this.count = count;
    }


    public HashMap<Integer, String> getPokemonsWithUrl() {
        var pokemonsNamesUrls = new HashMap<Integer, String>();
        for (var pokInfo : pokemonResults) {
            pokemonsNamesUrls.put(pokInfo.getPokemonName().hashCode(), pokInfo.getPokemonUrl());
        }
        return pokemonsNamesUrls;
    }
}
