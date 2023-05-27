package com.example.tacorebuild.controllers;


import com.example.tacorebuild.repos.TacoRepository;
import com.example.tacorebuild.structs.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos",produces = {"application/json","text/xml"})
@CrossOrigin(origins = "http://localhost:8080")
public class TacoController {

    @Autowired
    private TacoRepository tacoRepository;

    @GetMapping(params = "recent")
    public Iterable<Taco> orderForm() {
        Pageable pageable = PageRequest.of(0,12, Sort.by(Sort.Direction.DESC, "tacoOrder.createdAt"));
        Page<Taco> finded= tacoRepository.findAll(pageable);
        Iterable<Taco>data=finded.getContent();

        return data;}

    @GetMapping(value = "/{id}")
    public ResponseEntity orderForm(@PathVariable("id") Long id) {
        Optional<Taco> optionalTacos= tacoRepository.findById(id);
        if (!optionalTacos.isPresent())return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(optionalTacos.get(),HttpStatus.OK);}
}
