package com.example.tacorebuild.controllers;


import com.example.tacorebuild.repos.TacoRepository;
import com.example.tacorebuild.structs.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos",produces = {"application/json","text/xml"})
@CrossOrigin(origins = "http://localhost:8080")
public class TacoController {

    @Autowired
    private TacoRepository tacoRepository;

    @GetMapping(params = "recent")
    public Flux<Taco> recentTacos() {
        return Flux.fromIterable(tacoRepository.findAll()).take(12);
    }
    @GetMapping(params = "recentReact")
    public Flux<Taco> recentTacosReact() {
        return null;
//        return tacoRepositoryReact.findAll().take(12);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTacos= tacoRepository.findById(id);
        if (!optionalTacos.isPresent())return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(optionalTacos.get(),HttpStatus.OK);}

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }
//    @PostMapping(value = "/react",consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<Taco> postTacoReact(@RequestBody Mono<Taco> taco){
//        return tacoRepository.saveAll(taco).
//    }
}
