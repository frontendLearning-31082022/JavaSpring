package com.example.tacos.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.tacos.databaseStruct.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
  /*
	Iterable<Ingredient> findAll();
  Optional<Ingredient> findById(String id);
  Ingredient save(Ingredient ingredient); */
}