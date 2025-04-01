package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @ElementCollection
    private List<String> abilities;

    public Pokemon(Long id, String name, String type, List<String> abilities) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.abilities = abilities;
    }
}
