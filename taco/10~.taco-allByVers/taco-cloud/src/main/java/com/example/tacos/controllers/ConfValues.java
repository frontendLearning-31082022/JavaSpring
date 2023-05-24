package com.example.tacos.controllers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "taco.orders")
public class ConfValues {
    @Min(value = 5,message = "only 5-25")
    @Max(value = 25,message = "only 5-25")
    private int sizePage=20;
}
