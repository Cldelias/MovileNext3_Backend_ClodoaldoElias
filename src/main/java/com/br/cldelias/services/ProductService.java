package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.model.Product;
import com.br.cldelias.repositories.ProductRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public Product find(Integer id) {
		Optional<Product> op = this.getProductById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Product not found! Id: " + id + ", Type: " + Product.class.getName()));		
	}

	public Product save(Product entity) {
		return this.productRepo.save(entity);
	}
	
	public List<Product> saveAll(List<Product> entity) {
		return this.productRepo.saveAll(entity);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.productRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a product");
		}
	}
	
	
	public boolean isProductExist(Integer id) {
		Optional<Product> op = this.getProductById(id);
		return op.isPresent();
	}
	
	private Optional<Product> getProductById(Integer id) {
		return this.productRepo.findById(id);
	}

}
