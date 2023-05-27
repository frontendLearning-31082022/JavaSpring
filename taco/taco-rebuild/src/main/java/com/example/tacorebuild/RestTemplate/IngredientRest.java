package com.example.tacorebuild.RestTemplate;

import com.example.tacorebuild.structs.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class IngredientRest {
    @Autowired
    RestTemplate restTemplate;

    String url="http://localhost:8080/";

    public Ingredient getIngredientById(String ingId){
        return  restTemplate.getForObject(url+"ingredients/{id}", Ingredient.class,ingId);
    }
    public Ingredient getIngredientByIdMap(String ingId){
        Map<String,String> map=new HashMap<>();
        map.put("id",ingId);

        return  restTemplate.getForObject(url+"ingredients/{id}", Ingredient.class,map);
    }
    public Ingredient getIngredientByIdURI(String ingId){
        Map<String,String> map=new HashMap<>();
        map.put("id",ingId);
        URI uri= UriComponentsBuilder.fromHttpUrl(url+"ingredients/{id}").build(map);

        return  restTemplate.getForObject(uri, Ingredient.class);
    }

    public Ingredient getIngredientByIdEntity(String ingId){
        ResponseEntity<Ingredient>responseEntity=restTemplate.getForEntity(url+"ingredients/{id}", Ingredient.class,ingId);
        return  responseEntity.getBody();
    }

    public void putIngridient(Ingredient ingr){
        restTemplate.put(url+"ingredients/{id}",ingr,ingr.getId());
    }

    public void deleteIngridient(Ingredient ingr){
        restTemplate.delete(url+"ingredients/{id}",ingr.getId());
    }

    public Ingredient createIngredient(Ingredient ingr){
        return  restTemplate.postForObject(url+"ingredients", ingr,Ingredient.class);
    }


}
