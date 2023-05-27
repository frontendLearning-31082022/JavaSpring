package com.example.tacorebuild.repos;

import com.example.tacorebuild.structs.Taco;
import com.example.tacorebuild.structs.TacoOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    Page<Taco> findAll(Pageable pageable);
}