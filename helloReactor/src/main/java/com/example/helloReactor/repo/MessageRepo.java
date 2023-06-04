package com.example.helloReactor.repo;

import com.example.helloReactor.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepo extends ReactiveCrudRepository<Message,Long> { }