package fr.uge.jee.springmvc.pokematch.controller;


import fr.uge.jee.springmvc.pokematch.model.PokemonDto;
import fr.uge.jee.springmvc.pokematch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
public class PokeMatchController {

    @Autowired
    private PokedexHandler pokedexHandler;

    @GetMapping("/pokematch")
    public String getPokeMatch(Model model){
        model.addAttribute("user", new User());
        var topPokemons = pokedexHandler.topNPokemonsByOccurence( 10);
        model.addAttribute("topPokemons", topPokemons);

        return "pokematch-form.html";
    }

    @PostMapping("/pokematch")
    public String postPokeMatch(@Valid @ModelAttribute("user") User user,
                                BindingResult bindingResult,
                                Model model){

        if(bindingResult.hasErrors()){
            var topPokemons = pokedexHandler.topNPokemonsByOccurence( 10);
            model.addAttribute("topPokemons", topPokemons);
            model.addAttribute("user", user);
            return "pokematch-form.html";
        }

        var pok = pokedexHandler.determinePokemonByHashCode(user.hashCode());
        var topPokemons = pokedexHandler.topNPokemonsByOccurence( 10);
        model.addAttribute("topPokemons", topPokemons);
        user.setPokemon(pok);

        return "pokematch-form.html";

    }




}
