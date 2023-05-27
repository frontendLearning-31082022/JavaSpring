package com.example.tacorebuild.structs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;

@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=5,message="Name at least 5 ch")
    private String  name;
    @JsonIgnore
    @ManyToOne
    private TacoOrder tacoOrder;
    @ManyToMany
    private List<Ingredient> ingredients;

}