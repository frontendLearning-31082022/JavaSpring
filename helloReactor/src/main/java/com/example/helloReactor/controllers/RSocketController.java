package com.example.helloReactor.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class RSocketController {

    @MessageMapping("rsHello")
    public Mono<String> helloHandle(Mono<String>helloMono){
        return helloMono.doOnNext(x->log.info("Received on helloRs socket: {}",helloMono))
                .map(hell->"hello back");
    }

    @MessageMapping("handleReqVars/{var}")
    public Mono<String>handleReqVars(@DestinationVariable("var") String var, Mono<String>inpMono){
        return inpMono.doOnNext(x->log.info("Received on handleReqVars socket: {}",inpMono))
                .map(x->"Handled you var bit "+var);
    }

}
