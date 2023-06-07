package com.example.helloReactor.controllers;

import com.example.helloReactor.client.ClientRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
