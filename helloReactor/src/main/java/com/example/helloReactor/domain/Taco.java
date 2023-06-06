package com.example.helloReactor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.util.List;

@Data
@Table("taco")
public class Taco {
    @Id
    private Long id;
    private String  name;
    @JsonIgnore
    private TacoOrder tacoOrder;
    private List<Ingredient> ingredients;



}