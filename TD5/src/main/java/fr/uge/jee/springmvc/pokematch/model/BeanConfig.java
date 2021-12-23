package fr.uge.jee.springmvc.pokematch.model;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {


    @Bean
    WebClient createWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.build();
    }

    @Bean
    Pokedex createPokemonBdd(WebClient webClient){
        return Pokedex.FromApi(webClient);
    }

}
