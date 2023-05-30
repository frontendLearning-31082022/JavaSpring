package com.example.tacorebuild.SpringIntegration;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;

@Configuration
public class FilterEvenNumber {

    @Filter(inputChannel = "numberChannel",outputChannel = "evenNumberChannel")
    public boolean evenNumFilter(Integer num){
        return num % 2 == 0;
    }

}
