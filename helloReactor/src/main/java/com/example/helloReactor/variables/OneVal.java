package com.example.helloReactor.variables;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Component
@ConfigurationProperties("bean-var")
@Data
@Validated
public class OneVal {
    @Min(value = 1)
    private int valueOne=11;
}
