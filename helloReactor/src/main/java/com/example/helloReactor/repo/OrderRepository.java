package com.example.helloReactor.repo;

import com.example.helloReactor.domain.TacoOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, Long> { }