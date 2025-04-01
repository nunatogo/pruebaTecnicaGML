package com.pokemon.api.controller;

import com.pokemon.api.services.PokemonDbService;
import com.pokemon.api.services.PokemonService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public Mono<String> getPokemons(@RequestParam(defaultValue = "10") int limit,
                                    @RequestParam(defaultValue = "0") int offset) {
        return pokemonService.getPokemons(limit, offset);
    }
}
