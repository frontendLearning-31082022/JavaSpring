package com.example.tacorebuild.structs;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=5,message="Name at least 5 ch")
    private String  name;
    @ManyToOne
    private TacoOrder tacoOrder;
    @ManyToMany
    private List<Ingredient> ingredients;

}