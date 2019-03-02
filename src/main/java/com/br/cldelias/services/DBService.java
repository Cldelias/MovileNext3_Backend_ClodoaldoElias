package com.br.cldelias.services;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cldelias.enums.EnumDayWeek;
import com.br.cldelias.model.Category;
import com.br.cldelias.model.Client;
import com.br.cldelias.model.Operation;
import com.br.cldelias.model.Product;
import com.br.cldelias.model.Restaurant;

@Service
public class DBService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OperationService operationService;
	
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ClientService clientService;
	
	public void instatiateTestDatabase( ) throws Exception {
		Category cat1 = new Category(null, "Lanches");
		Category cat2 = new Category(null, "Pizzas");
		
		Product prd1 = new Product.Builder()
				.withName("X TUDO")
				.withPrice(new Double(10.50))
				.withCategory(cat1).build();
		
		Product prd2 = new Product.Builder()
				.withName("X SALADA")
				.withPrice(new Double(8.50))
				.withCategory(cat1).build();
		
		Product prd3 = new Product.Builder()
				.withName("PORTUGUESA")
				.withPrice(new Double(38.50))
				.withCategory(cat2).build();
		
		Product prd4 = new Product.Builder()
				.withName("CALABRESA")
				.withPrice(new Double(32.50))
				.withCategory(cat2).build();
		
		List<Operation> operations = Arrays.asList(EnumDayWeek.values()).stream().filter(d -> d.getDayWeek() >= 2 && d.getDayWeek() <= 7).map(d -> {
			Operation op = new Operation.Builder()
					.atTheDay(d)
					.openThe(LocalTime.of(8, 0))
					.closed(LocalTime.of(18, 0))
					.build();
			return op;
		}).collect(Collectors.toList());
		
		Restaurant rest1 = new Restaurant.Builder()
				.withName("LANCHONETE BOM BOI")
				.addListOperation(operations)
				.build();
		
		Restaurant rest2 = new Restaurant.Builder()
				.withName("LANCHONETE CARNE LOUCA")
				.addListOperation(operations)
				.build();
		
		Restaurant rest3 = new Restaurant.Builder()
				.withName("PIZZARIA MAMA MIA")
				.addListOperation(operations)
				.build();
		
		Restaurant rest4 = new Restaurant.Builder()
				.withName("PIZZARIA SAO RAFAEL")
				.addListOperation(operations)
				.build();
		
		Client cl1 = new Client.Builder()
				.withName("CLIENTE 1")
				.withEmail("CLIENTE1@GMAIL.COM")
				.withPassword("123")
				.build();
		
		Client cl2 = new Client.Builder()
				.withName("CLIENTE 2")
				.withEmail("CLIENTE2@GMAIL.COM")
				.withPassword("123")
				.build();
		
		this.categoryService.saveAll(Arrays.asList(cat1, cat2));
		
		this.productService.saveAll(Arrays.asList(prd1, prd2, prd3, prd4));
		
		this.operationService.saveAll(operations);
		
		this.restaurantService.saveAll(Arrays.asList(rest1, rest2, rest3, rest4));
		
		this.clientService.saveAll(Arrays.asList(cl1, cl2));
	}


}
