package com.example.tacorebuild.repos;

import com.example.tacorebuild.structs.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}