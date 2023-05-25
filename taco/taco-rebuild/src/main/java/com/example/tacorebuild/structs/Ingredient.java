package com.example.tacorebuild.structs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        KETCHUP, WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE,
    }
}