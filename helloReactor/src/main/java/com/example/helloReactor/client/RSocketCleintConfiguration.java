package com.example.helloReactor.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
@Slf4j
public class RSocketCleintConfiguration {
    @Bean
    public ClientSender clientSend(RSocketRequester.Builder reqBuild){
        ClientSender clientSender=new ClientSender() {
            @Override
            public void sendReqAnswer(String urlPath, String msg) {
                RSocketRequester tcp=reqBuild.tcp("localhost",7000);
                tcp.route(urlPath).data(msg)
                        .retrieveMono(String.class).subscribe(x->log.info("Responce from server: {} and msg data {}",x,msg));
            }
        };
        return clientSender;
    }

}