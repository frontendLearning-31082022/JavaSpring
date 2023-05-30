package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelPublishSubscribe {

    @Bean
    public MessageChannel orderChannel(){
        return new PublishSubscribeChannel();
    }

    @ServiceActivator(inputChannel = "orderChannel",poller = @Poller(fixedRate = "1000"))
    void someService(){}

}
