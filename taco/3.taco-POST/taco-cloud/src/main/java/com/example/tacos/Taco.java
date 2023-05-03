package com.example.tacos;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	
	@NotNull
	@Size(min=5,message="Name at least 5 ch")
	private String  name;
	
	@NotNull
	@Size(min=1,message="at least 1 ingridient")
	private List<Ingredient> ingredients;
	public Taco(String name, List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Taco() {
		// TODO Auto-generated constructor stub
	}
}
