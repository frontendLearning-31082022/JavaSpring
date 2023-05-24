package com.example.tacos.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.tacos.databaseStruct.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
	/*
	  TacoOrder save(TacoOrder order);
	  Optional<TacoOrder> findById(Long id); */
}