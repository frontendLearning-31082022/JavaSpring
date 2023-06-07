package com.example.helloReactor.controllers;

import com.example.helloReactor.client.ClientRS;
import com.example.helloReactor.domain.Alert;
import com.example.helloReactor.domain.Benefit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.function.Consumer;

@RestController
@Slf4j
public class RSocketClientController {
    @Autowired
    ClientRS clientSend;

    @GetMapping("clientRS/sendReqAnswer")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String clientSendReqAnswer(
    ){
        clientSend.sendReqAnswer("handleReqVars/AbuDabi","Privet");
        return "ok";
    }
    @GetMapping("clientRS/fluxListener")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    @ResponseBody
    public String fluxListener(
    ){
        clientSend.fluxListener("stock/goldenRuble");
        return "ok";
    }
    @GetMapping("clientRS/reqOneWay")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    @ResponseBody
    public String reqOneWay(
    ){
        clientSend.sendReqOneWay("alert",new Alert(Alert.Level.RED,"tempCore", Instant.now()));
        return "ok";
    }
    @GetMapping("clientRS/fluxMessaging")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    @ResponseBody
    public String fluxMessaging(
    ){
        Flux<Object> testData=Flux.fromArray(new Benefit[]{
                new Benefit(BigDecimal.valueOf(35.50),18),
                new Benefit(BigDecimal.valueOf(10.00),15),
                new Benefit(BigDecimal.valueOf(23.25),20),
                new Benefit(BigDecimal.valueOf(52.75),18),
                new Benefit(BigDecimal.valueOf(80.00),15)
        });

        Consumer<Object> objectConsumer = x ->
        {
            Map<String,Object>m= (Map<String, Object>) x;
           log.info(m.get("percent")+"% benefit on "+m.get("totalSum")+" is "+m.get("benef"));
        };

        clientSend.fluxMessaging("benefit",testData,objectConsumer);
        return "ok";
    }
}
