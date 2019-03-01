package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.model.Category;
import com.br.cldelias.repositories.CategoryRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	public Category find(Integer id) {
		Optional<Category> op = this.categoryRepo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"Category not found! Id: " + id + ", Type: " + Category.class.getName()));		
	}

	public Category save(Category entity) {
		return this.categoryRepo.save(entity);
	}

	public List<Category> saveAll(List<Category> list) {
		return this.categoryRepo.saveAll(list);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.categoryRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a category that has products");
		}
	}


}
