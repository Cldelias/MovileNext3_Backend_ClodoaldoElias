package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.model.Operation;
import com.br.cldelias.repositories.OperationRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;

@Service
public class OperationService {
	
	@Autowired
	private OperationRepository OperationRepo;

	public Operation find(Integer id) {
		Optional<Operation> op = this.OperationRepo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Operation not found! Id: " + id + ", Type: " + Operation.class.getName()));		
	}

	public Operation save(Operation entity) {
		return this.OperationRepo.save(entity);
	}

	public List<Operation> saveAll(List<Operation> entity) {
		return this.OperationRepo.saveAll(entity);
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			this.OperationRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a Operation");
		}
	}


}
