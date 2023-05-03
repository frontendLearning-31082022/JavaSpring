package com.example.tacos.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
	/*
	  TacoOrder save(TacoOrder order);
	  Optional<TacoOrder> findById(Long id); */
}