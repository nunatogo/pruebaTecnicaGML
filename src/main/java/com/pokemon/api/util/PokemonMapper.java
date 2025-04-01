package com.pokemon.api.util;

import com.pokemon.api.model.Pokemon;
import com.pokemon.api.model.PokemonEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonMapper {

    public static PokemonEntity toEntity(Pokemon pokemon) {
        return new PokemonEntity(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType(),
                pokemon.getAbilities()
        );
    }

    public static List<PokemonEntity> toEntityList(List<Pokemon> pokemons) {
        return pokemons.stream()
                .map(PokemonMapper::toEntity)
                .collect(Collectors.toList());
    }
}
