package com.pokemon.api.services.impl;

import com.example.pokemonapi.ws.GetAllPokemonsResponse;
import com.pokemon.api.model.PokemonEntity;
import com.pokemon.api.repository.PokemonRepository;
import com.pokemon.api.services.PokemonDbService;
import com.pokemon.api.services.PokemonService;
import com.pokemon.api.util.PokemonSoapMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonDbServiceImpl implements PokemonDbService {

    private final PokemonRepository pokemonRepository;
    private final PokemonService pokemonService;

    @Autowired
    public PokemonDbServiceImpl(PokemonRepository pokemonRepository, PokemonService pokemonService) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonService = pokemonService;
    }

    @Override
    public void loadPokemonsFromApi(int limit, int offset) {
        pokemonService.getPokemons(limit, offset)
                .flatMapMany(response -> {
                    JSONObject json = new JSONObject(response);
                    JSONArray results = json.getJSONArray("results");
                    List<Mono<PokemonEntity>> pokemonMonos = new ArrayList<>();

                    for (int i = 0; i < results.length(); i++) {
                        String url = results.getJSONObject(i).getString("url");
                        Mono<PokemonEntity> mono = pokemonService.getPokemonDetail(url)
                                .map(this::mapJsonToPokemonEntity);
                        pokemonMonos.add(mono);
                    }

                    return Flux.merge(pokemonMonos);
                })
                .collectList()
                .doOnNext(pokemonRepository::saveAll)
                .block();
    }

    @Override
    public PokemonEntity mapJsonToPokemonEntity(String jsonDetail) {
        JSONObject json = new JSONObject(jsonDetail);
        PokemonEntity entity = new PokemonEntity();
        entity.setName(json.getString("name"));
        entity.setType(json.getJSONArray("types")
                .getJSONObject(0)
                .getJSONObject("type")
                .getString("name"));
        List<String> abilities = new ArrayList<>();
        JSONArray jsonAbilities = json.getJSONArray("abilities");
        for (int i = 0; i < jsonAbilities.length(); i++) {
            abilities.add(jsonAbilities.getJSONObject(i)
                    .getJSONObject("ability")
                    .getString("name"));
        }
        entity.setAbilities(abilities);
        return entity;
    }

    @Override
    public List<PokemonEntity> getAllPokemonsFromDb() {
        return pokemonRepository.findAll();
    }

    @Override
    public GetAllPokemonsResponse getAllPokemonsResponse() {
        List<PokemonEntity> entities = pokemonRepository.findAll();
        List<com.pokemon.api.model.Pokemon> models = entities.stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
        return PokemonSoapMapper.toSoapResponse(models);
    }

    private com.pokemon.api.model.Pokemon mapEntityToModel(PokemonEntity entity) {
        return new com.pokemon.api.model.Pokemon(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getAbilities()
        );
    }
}