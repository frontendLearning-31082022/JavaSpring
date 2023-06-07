package com.example.helloReactor.controllers;

import com.example.helloReactor.domain.Benefit;
import com.example.helloReactor.domain.BenefitCalculated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Controller
@Slf4j
public class BenefitController {

    @MessageMapping("benefit")
    public Flux<BenefitCalculated> calculate(Flux<Benefit> benefitIn){
        return benefitIn.doOnNext(in->log.info("Calculating for {} ",in))
                .map(in->{
                    double percentAsDecimal=in.getPercent()/100.0;
                    BigDecimal benfit=in.getTotalSum().multiply(BigDecimal.valueOf(percentAsDecimal));
                    return new BenefitCalculated(in.getTotalSum(),in.getPercent(),benfit);
                });
    }

}