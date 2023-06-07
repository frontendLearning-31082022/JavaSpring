package com.example.helloReactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BenefitCalculated {

    private BigDecimal totalSum;
    private int percent;
    private BigDecimal benef;

}