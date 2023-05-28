package com.example.tacorebuild.controllers;

import com.example.tacorebuild.repos.IngredientRepository;
import com.example.tacorebuild.structs.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/inredients",produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {
    @Autowired
    IngredientRepository repoIngr;

    @GetMapping(params = "recent")
    public Iterable<Ingredient> allIngredients() {
        return repoIngr.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return repoIngr.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteIngredient(@PathVariable("id") String ingrId){
        repoIngr.deleteById(ingrId);
    }
}
