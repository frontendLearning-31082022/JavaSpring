package com.example.helloReactor.client;

public interface ClientRS {
    void sendReqAnswer(String urlPath, String msg);
    void fluxListener(String urlPath);
    void sendReqOneWay(String urlPath, Object obj);
}
