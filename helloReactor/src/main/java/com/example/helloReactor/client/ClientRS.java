package com.example.helloReactor.client;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public interface ClientRS {
    void sendReqAnswer(String urlPath, String msg);
    void fluxListener(String urlPath);
    void sendReqOneWay(String urlPath, Object obj);
    void fluxMessaging(String urlPath, Flux<Object> in, Consumer<Object> objectConsumer);
}
