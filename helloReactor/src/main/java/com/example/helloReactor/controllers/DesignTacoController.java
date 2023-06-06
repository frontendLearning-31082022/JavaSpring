package com.example.helloReactor.controllers;

import com.example.helloReactor.domain.Ingredient;
import com.example.helloReactor.domain.Taco;
import com.example.helloReactor.domain.TacoOrder;

import com.example.helloReactor.repo.IngredientRepository;
import io.r2dbc.spi.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
public class DesignTacoController {
    public DesignTacoController(IngredientRepository ingrRepo) {this.ingrRepo = ingrRepo;}

    @Autowired
    IngredientRepository ingrRepo;

    public void addIngredientsToModel(Rendering.Builder<?> view) {
        for (String type : Ingredient.getTypes()) { //
            view.modelAttribute(type.toLowerCase(),ingrRepo.findAll().filter(x->type.equals(Ingredient.getTypes()[x.getType()])));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {

        return new TacoOrder();
//        return Mono.just(new TacoOrder());
    }

//    @ModelAttribute(name = "taco")
//    public Mono<Taco> taco() {
//        return Mono.just(new Taco());
//    }

        @GetMapping
    public Mono<Rendering> showDesignForm() {
        Rendering.Builder<?> view=Rendering.view("design");
        addIngredientsToModel(view);

        Taco taco=new Taco();
        taco.setIngredients(new ArrayList<>());
        view.modelAttribute("taco",taco);

        view.modelAttribute("tacoOrder",new TacoOrder());


        return Mono.just(view.build());
        }

    @PostMapping
    public String processTaco(Taco taco, Errors errors,
                              @ModelAttribute("tacoOrder") TacoOrder tacoOrder, ServerWebExchange webRequest, WebSession webSession) {


        ArrayList<String>ingrStream=new ArrayList<>();
        webRequest.getFormData().map(x->x.get("ingredients")).subscribe(x->ingrStream.add(String.valueOf(x)));
        //костыль
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> ingr=new ArrayList<>();
        ingrStream.forEach(x->{
            ingr.addAll( new ArrayList<>(Arrays.asList(  x.replaceAll("[^A-z,]","").replaceAll("(\\[|\\])","").split(","))));
        });

        ArrayList<Ingredient>ingForTaco=new ArrayList<>();
        ingr.stream().map(x-> ingrRepo.findById(x)).collect(Collectors.toList())
                .forEach(x->x.subscribe(y->ingForTaco.add(y)));
        //костыль
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        taco.setIngredients(ingForTaco);

        tacoOrder=new TacoOrder();
        tacoOrder.addTaco(taco);

//        if(errors.hasErrors()) {return "design";}

        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}


