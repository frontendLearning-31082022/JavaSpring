package com.example.helloReactor.controllers;

import com.example.helloReactor.domain.Alert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class AlertController {
    @MessageMapping("alert")
    public Mono<Void> setAlert(Mono<Alert> alertMono){
        return alertMono.doOnNext(alert ->
                log.info("{} alert by {} at {}",alert.getLevel(),alert.getOrderBy(),alert.getTimeStamp())
                ).thenEmpty(Mono.empty());
    }

}