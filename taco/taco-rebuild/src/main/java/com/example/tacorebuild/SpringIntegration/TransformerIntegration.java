package com.example.tacorebuild.SpringIntegration;


import com.example.tacorebuild.structs.TacoOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;

@Configuration
public class TransformerIntegration {
    @Bean
    @Transformer
    TacoOrder generateOrder(String productId) {
        return new TacoOrder();
    }
}
