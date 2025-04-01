package com.pokemon.api.util;

import com.example.pokemonapi.ws.GetAllPokemonsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonSoapMapper {

    public static GetAllPokemonsResponse toSoapResponse(List<com.pokemon.api.model.Pokemon> pokemons) {
        GetAllPokemonsResponse response = new GetAllPokemonsResponse();
        List<com.example.pokemonapi.ws.Pokemon> soapPokemons = pokemons.stream()
                .map(PokemonSoapMapper::toSoapPokemon)
                .collect(Collectors.toList());
        response.getPokemons().addAll(soapPokemons);
        return response;
    }

    private static com.example.pokemonapi.ws.Pokemon toSoapPokemon(com.pokemon.api.model.Pokemon pokemon) {
        com.example.pokemonapi.ws.Pokemon soap = new com.example.pokemonapi.ws.Pokemon();
        soap.setId(pokemon.getId());
        soap.setName(pokemon.getName());
        soap.setType(pokemon.getType());
        soap.getAbilities().addAll(pokemon.getAbilities());
        return soap;
    }
}
