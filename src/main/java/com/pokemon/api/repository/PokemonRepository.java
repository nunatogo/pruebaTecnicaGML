package com.pokemon.api.repository;

import com.pokemon.api.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    List<PokemonEntity> findByNameContainingIgnoreCase(String name);

    List<PokemonEntity> findByTypeIgnoreCase(String type);

    List<PokemonEntity> findByAbilitiesContainingIgnoreCase(String ability);
}
