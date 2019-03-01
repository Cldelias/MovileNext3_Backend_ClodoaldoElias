package com.br.cldelias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cldelias.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer>{


}
