package com.example.tacorebuild.structs;

import lombok.Data;

import java.util.List;

@Data
public class Taco {
    private String  name;
    private List<Ingredient> ingredients;
}