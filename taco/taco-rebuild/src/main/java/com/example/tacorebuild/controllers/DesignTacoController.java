package com.example.tacorebuild.controllers;

import com.example.tacorebuild.repos.IngredientRepository;
import com.example.tacorebuild.repos.OrderRepository;
import com.example.tacorebuild.structs.Ingredient;
import com.example.tacorebuild.structs.Taco;
import com.example.tacorebuild.structs.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tacorebuild.structs.Ingredient.Type;



@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
public class DesignTacoController {
    @Autowired
    IngredientRepository ingrRepo;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients=ingrRepo.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());		  }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {return "design";}

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}


