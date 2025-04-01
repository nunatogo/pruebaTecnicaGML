package com.pokemon.api.services;

import com.example.pokemonapi.ws.GetAllPokemonsResponse;
import com.pokemon.api.model.PokemonEntity;

import java.util.List;

public interface PokemonDbService {

    void loadPokemonsFromApi(int limit, int offset);

    PokemonEntity mapJsonToPokemonEntity(String jsonDetail);

    List<PokemonEntity> getAllPokemonsFromDb();

    GetAllPokemonsResponse getAllPokemonsResponse();
}
