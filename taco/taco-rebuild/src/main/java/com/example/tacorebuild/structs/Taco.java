package com.example.tacorebuild.structs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min=5,message="Name at least 5 ch")
    private String  name;
    private List<Ingredient> ingredients;
}