package com.example.tacorebuild.Converters;

import com.example.tacorebuild.repos.IngredientRepository;
import com.example.tacorebuild.structs.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import com.example.tacorebuild.structs.Ingredient.Type;
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    @Autowired
    IngredientRepository ingrRepo;

    @Override
    public Ingredient convert(String id) {
        return ingrRepo.findById(id).orElse(null);
    }
}