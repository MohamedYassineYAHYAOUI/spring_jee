package fr.uge.jee.springmvc.pokematch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultsDto {

    @JsonProperty("name")
    private String pokemonName;

    @JsonProperty("url")
    private String pokemonUrl;

    public void setPokemonUrl(String pokemonUrl) {
        this.pokemonUrl = pokemonUrl;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonUrl() {
        return pokemonUrl;
    }

    public String getPokemonName() {
        return pokemonName;
    }
}
