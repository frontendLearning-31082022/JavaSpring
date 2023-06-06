package com.example.helloReactor.repo;

import com.example.helloReactor.domain.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> { }