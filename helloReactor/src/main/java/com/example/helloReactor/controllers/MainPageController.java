package com.example.helloReactor.controllers;

import com.example.helloReactor.domain.Message;
import com.example.helloReactor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/main")
public class MainPageController {
    @Autowired
    private final MessageService messageService;
    public MainPageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "3") Long count
    ){
        return messageService.list();
    }

    @PostMapping
    public Mono<Message> add(@RequestBody Message message){
        return messageService.addOne(message);
    }

    @GetMapping("pid")
    public String pid(
    ){
       return System.getProperty("PID");
    }

}
