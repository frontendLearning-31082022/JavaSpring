package com.example.tacos.databaseStruct;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Taco {
	
	@Id
	private Long id;
	private Date createdAt=new Date();
	
	@NotNull
	@Size(min=5,message="Name at least 5 ch")
	private String  name;
	
	@NotNull
	@Size(min=1,message="at least 1 ingridient")
	private List<IngredientRef> ingredients;
	public Taco(String name, List<IngredientRef> ingredients) {
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
	

	public List<IngredientRef> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientRef> ingredients) {
		this.ingredients = ingredients;
	}
	public Taco() {
		// TODO Auto-generated constructor stub
	}
}
