package com.pokemon.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PokemonService {

    private static final String URL = "https://pokeapi.co/api/v2";
    private final WebClient webClient;


    public PokemonService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(URL).build();
    }

    public Mono<String> getPokemons(int limit, int offset) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/pokemon")
                        .queryParam("limit", limit)
                        .queryParam("offset", offset)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPokemonDetail(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
