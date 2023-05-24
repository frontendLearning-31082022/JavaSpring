package com.example.tacos.databaseStruct;

import org.springframework.security.access.prepost.PreAuthorize;

public class IngredientRef {
  private final String ingredient;

public String getIngredient() {
	return ingredient;
}

public IngredientRef(String ingredient) {
	super();
	this.ingredient = ingredient;
}

}