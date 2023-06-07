package com.example.helloReactor.client;

import com.example.helloReactor.domain.StockQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
@Slf4j
public class RSocketCleintConfiguration {
    @Bean
    public ClientRS clientRS(RSocketRequester.Builder reqBuild){
        ClientRS clientSender=new ClientRS() {
            @Override
            public void sendReqAnswer(String urlPath, String msg) {
                RSocketRequester tcp=reqBuild.tcp("localhost",7000);
                tcp.route(urlPath).data(msg)
                        .retrieveMono(String.class).subscribe(x->log.info("Responce from server: {} and msg data {}",x,msg));
            }
            @Override
            public void fluxListener(String urlPath) {
                RSocketRequester tcp=reqBuild.tcp("localhost",7000);
                tcp.route(urlPath)
                        .retrieveFlux(StockQuote.class)
                        .doOnNext(one->log.info("Price of {} - {} (time {})",one.getSymbol(),one.getPrice(),one.getTimestamp()))
                        .subscribe();
            }
        };
        return clientSender;
    }

}