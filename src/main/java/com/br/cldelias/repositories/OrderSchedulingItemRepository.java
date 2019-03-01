package com.br.cldelias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cldelias.model.OrderSchedulingItem;

@Repository
public interface OrderSchedulingItemRepository extends JpaRepository<OrderSchedulingItem, Integer>{

}
