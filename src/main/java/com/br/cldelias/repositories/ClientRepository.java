package com.br.cldelias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cldelias.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{


}
