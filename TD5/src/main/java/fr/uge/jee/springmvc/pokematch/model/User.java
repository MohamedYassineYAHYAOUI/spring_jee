package fr.uge.jee.springmvc.pokematch.model;


import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Base64;
import java.util.Objects;

@Component
public class User {


    @NotBlank(message = "Must not be blank or null")
    private String firstName;

    @NotBlank(message = "Must not be blank or null")
    private String lastName;

    private PokemonDto pokemon;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPokemon(PokemonDto pokemon) {
        this.pokemon = pokemon;
    }

    public String getPokemon(){
        return hasPokemon() ? pokemon.getName() : null;
    }

    public String getUserImage(){
        return pokemon.getImageBase64();
    }

    public boolean hasPokemon(){
        return this.pokemon != null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(pokemon, user.pokemon);
    }

    @Override
    public int hashCode() {
        return (firstName + lastName).hashCode();
    }
}