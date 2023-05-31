package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.atomic.AtomicInteger;

public class AdapterIn {
    @Bean
    @InboundChannelAdapter(channel = "numCh")
    public MessageSource<Integer> numSrc(AtomicInteger src){
        return () -> {
            return new GenericMessage<>(src.getAndIncrement());
        };
    }
}
