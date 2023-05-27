package com.example.tacorebuild.repos;

import com.example.tacorebuild.structs.TacoOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    Page<TacoOrder> findAll(Pageable pageable);
}