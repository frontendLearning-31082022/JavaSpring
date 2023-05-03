package com.example.tacos;

import org.springframework.data.annotation.Id;

public class Ingredient {
	@Id
	private final String id;
	private final String name;
	private final Type type;
	
	
	public Type getType() {
		return type;
	}


	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public Ingredient(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}


	public enum Type {
		WRAP, PROTEIN, VEGGEIS, CHEESE, SAUCE,VEGGIES
	}	
}