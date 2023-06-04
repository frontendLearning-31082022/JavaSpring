package com.example.helloReactor.handlers;

import com.example.helloReactor.domain.Content;
import com.example.helloReactor.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start").map(Long::valueOf).orElse(0L);
        Long count = request.queryParam("count").map(Long::valueOf).orElse(3L);

        Flux<Content> data= Flux.just("Heello","More then one","third post","fourth post")
                .skip(start).take(count)
                .map(Content::new);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(data,Message.class);
    }

    public Mono<ServerResponse> mainPage(ServerRequest request) {
        String userName = request.queryParam("user").orElse("Nobody");
        return ServerResponse.ok().render("index", Map.of("user",userName));
    }


}