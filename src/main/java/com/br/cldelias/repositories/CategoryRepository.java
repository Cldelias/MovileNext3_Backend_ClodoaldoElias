package com.br.cldelias.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.cldelias.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{


}
