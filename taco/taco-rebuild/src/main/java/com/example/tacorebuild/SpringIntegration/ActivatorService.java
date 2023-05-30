package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Configuration
public class ActivatorService {
    @Bean
    @ServiceActivator(inputChannel = "billingInfoChannel")
    public MessageHandler sysOutHandler(){
        return message -> {System.out.println("(completed) Message  "+message.getPayload());};
    }
}
