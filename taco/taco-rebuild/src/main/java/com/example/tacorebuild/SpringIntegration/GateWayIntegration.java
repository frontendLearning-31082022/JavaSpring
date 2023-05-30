package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Configuration
public class GateWayIntegration {
    @Component
    @MessagingGateway(defaultRequestChannel = "inCh",defaultReplyChannel = "outCh")
    public interface toLowCase{
        String lowercase(String in);
    }

}
