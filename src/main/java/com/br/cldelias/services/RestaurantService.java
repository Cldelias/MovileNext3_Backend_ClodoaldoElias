package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.model.Restaurant;
import com.br.cldelias.repositories.RestaurantRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository RestaurantRepo;

	public Restaurant find(Integer id) {
		Optional<Restaurant> op = this.getRestaurantById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Restaurant not found! Id: " + id + ", Type: " + Restaurant.class.getName()));		
	}

	public Restaurant save(Restaurant entity) {
		return this.RestaurantRepo.save(entity);
	}
	
	public List<Restaurant> saveAll(List<Restaurant> entity) {
		return this.RestaurantRepo.saveAll(entity);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.RestaurantRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a Restaurant");
		}
	}
	
	private Optional<Restaurant> getRestaurantById(Integer id) {
		return this.RestaurantRepo.findById(id);		
	}


}
