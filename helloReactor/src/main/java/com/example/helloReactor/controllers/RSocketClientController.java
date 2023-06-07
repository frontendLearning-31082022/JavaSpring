package com.example.helloReactor.controllers;

import com.example.helloReactor.client.ClientRS;
import com.example.helloReactor.domain.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
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
}
