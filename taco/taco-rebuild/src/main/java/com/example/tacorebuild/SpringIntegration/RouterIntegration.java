package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class RouterIntegration {

    @Bean
    @Router(inputChannel = "numburChannel")
    public AbstractMessageRouter evenODDnums(){
        return new AbstractMessageRouter() {
            @Override
            protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
                Integer number= (Integer) message.getPayload();
                if (number%2==0)return Collections.singleton(evenChannel());
                return Collections.singleton(oddChannel());
            }
        };
    }

    @Bean
    public MessageChannel evenChannel(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel oddChannel(){
        return new DirectChannel();
    }

}
