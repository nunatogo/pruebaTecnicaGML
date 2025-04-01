package com.pokemon.api.endpoint;

import com.example.pokemonapi.ws.GetAllPokemonsRequest;
import com.example.pokemonapi.ws.GetAllPokemonsResponse;
import com.pokemon.api.services.PokemonDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/pokemon";

    @Autowired
    private PokemonDbService pokemonDbService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllPokemonsRequest")
    @ResponsePayload
    public GetAllPokemonsResponse getAllPokemons(@RequestPayload GetAllPokemonsRequest request) {
        return pokemonDbService.getAllPokemonsResponse();
    }
}
