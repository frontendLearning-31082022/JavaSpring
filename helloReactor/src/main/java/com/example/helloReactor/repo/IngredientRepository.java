package com.example.helloReactor.repo;

import com.example.helloReactor.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> { }