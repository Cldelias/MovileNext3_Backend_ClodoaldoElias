package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.model.Client;
import com.br.cldelias.repositories.ClientRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository ClientRepo;

	public Client find(Integer id) {
		Optional<Client> op = this.getClientById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Client not found! Id: " + id + ", Type: " + Client.class.getName()));		
	}

	public Client save(Client entity) {
		return this.ClientRepo.save(entity);
	}
	
	public List<Client> saveAll(List<Client> entity) {
		return this.ClientRepo.saveAll(entity);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.ClientRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a Client");
		}
	}
	
	public boolean isClientExist(Integer id) {
		Optional<Client> op = this.getClientById(id);
		return op.isPresent();
	}
	
	private Optional<Client> getClientById(Integer id) {
		return this.ClientRepo.findById(id);
		
	}
	

}
