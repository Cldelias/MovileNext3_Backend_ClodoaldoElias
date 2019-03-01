package com.br.cldelias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cldelias.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{


}
