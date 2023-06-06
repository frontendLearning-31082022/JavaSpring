package com.example.helloReactor.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;


@Data
@Table("ingredient")
public class Ingredient {
    @Id
    private String id;
    private String name;

    private int type;

    public static String[] getTypes(){
      return Arrays.stream(Typesss.values()).map(x->x.toString()).toArray(String[]::new);
    }
}
enum Typesss {
    KETCHUP, WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE,
}