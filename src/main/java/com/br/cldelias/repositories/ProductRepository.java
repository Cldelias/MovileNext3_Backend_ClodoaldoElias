package com.br.cldelias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cldelias.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
