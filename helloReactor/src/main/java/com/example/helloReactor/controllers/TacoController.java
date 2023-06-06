package com.example.helloReactor.controllers;

import com.example.helloReactor.domain.Taco;
import com.example.helloReactor.handlers.GreetingHandler;
import com.example.helloReactor.repo.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
@Configuration
public class TacoController {
    @Autowired
    private TacoRepository tacoRepository;

    @Bean
    public RouterFunction<ServerResponse> routeTaco(TacoController tacoController) {
        return RouterFunctions
                .route(GET("/api/tacos")
                                .and(queryParam("recent",t->t!=null)),
                        this::recents)
                .andRoute(RequestPredicates.POST("/api/tacos"),this::postTaco);
    }

    public Mono<ServerResponse> postTaco(ServerRequest request){
        return request.bodyToMono(Taco.class)
                .flatMap(taco -> tacoRepository.save(taco))
                .flatMap(savedTaco ->{
                    return  ServerResponse
                            .created(URI.create(
                                    "http://localhost:8080/api/tacos"
                                    +savedTaco.getId()
                            ))
                            .body(savedTaco,Taco.class);
                });
    }


    public Mono<ServerResponse> recents(ServerRequest request){
        return ServerResponse.ok()
                .body(tacoRepository.findAll().take(12),Taco.class);
    }







}
