package com.example.tacos.data;

import java.util.Optional;

import com.example.tacos.TacoOrder;

public interface OrderRepository {
	  TacoOrder save(TacoOrder order);
	  Optional<TacoOrder> findById(Long id);
}