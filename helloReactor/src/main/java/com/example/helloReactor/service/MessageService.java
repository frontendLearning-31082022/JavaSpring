package com.example.helloReactor.service;

import com.example.helloReactor.domain.Message;
import com.example.helloReactor.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {
    @Autowired
    private final MessageRepo messageRepo;
    public MessageService(MessageRepo messageRepo) {this.messageRepo = messageRepo;}

    public Flux<Message> list(){
        return messageRepo.findAll();
    }

    public Mono<Message> addOne(Message message){
        return messageRepo.save(message);
    }

}
