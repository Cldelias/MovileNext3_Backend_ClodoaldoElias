package com.br.cldelias.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.cldelias.enums.EnumTypeOperation;
import com.br.cldelias.model.OrderScheduling;

@Repository
public interface OrderSchedulingRepository extends JpaRepository<OrderScheduling, Integer>{

	@Transactional(readOnly=true)
	List<OrderScheduling> findByType(EnumTypeOperation typeOperation);

	@Query("SELECT obj FROM OrderScheduling obj WHERE obj.client.id = :clientId")
	public List<OrderScheduling> findByIdClient(@Param("clientId")Integer estado_id);

}
