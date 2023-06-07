package com.example.helloReactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Benefit {

    private BigDecimal totalSum;
    private int percent;

}