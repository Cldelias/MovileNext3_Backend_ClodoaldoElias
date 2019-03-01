package br.com.cldelias.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.cldelias.enums.EnumDayWeek;
import com.br.cldelias.enums.EnumTypeOperation;
import com.br.cldelias.model.Restaurant;
import com.br.cldelias.resources.exceptions.FieldMessage;
import com.br.cldelias.services.ClientService;
import com.br.cldelias.services.ProductService;
import com.br.cldelias.services.RestaurantService;

import br.com.cldelias.dto.OrderSchedulingNewDTO;

public class OrderSchedulingInsertValidator implements ConstraintValidator<OrderSchedulingInsert, OrderSchedulingNewDTO> {

	
	@Autowired
	private ClientService clientService;
	
	@Autowired 
	private RestaurantService restaurantService;
	
	@Autowired 
	private ProductService productService;

	
	@Override
	public void initialize(OrderSchedulingInsert ann) {
	}
	
	@Override
	public boolean isValid(OrderSchedulingNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		/** verifica se o cliente esta cadastrado na base **/
		if (!this.clientService.isClientExist(objDto.getIdClient())) {
			list.add(new FieldMessage("client", "Client not found"));
		}
		
		/** verifica se o restaurante esta cadastrado na base **/
		Restaurant restaurant = this.restaurantService.find(objDto.getIdRestaurant());
		if (restaurant == null) {
			list.add(new FieldMessage("restaurant", "Restaurant not found"));
		}
		
		/** verifica o dia informado é valido **/
		if (!EnumDayWeek.isDayWeekValid(objDto.getDay())) {
			list.add(new FieldMessage("Day", "Day of the week invalid - expected 1 the 7"));
		}
		
		/** verifica o tipo de operação é valido **/
		if (!EnumTypeOperation.isTypeOperationValid(objDto.getType())) {
			list.add(new FieldMessage("Type", "type of operation invalid - ezxpected 1 - SCHEDULED or 2 = ALERT"));
		}
		
		/** verifica se o restaurante atende no dia/hora agendado o pedido **/
		if (restaurant != null && EnumDayWeek.isDayWeekValid(objDto.getDay())) {
			if (!restaurant.isOperations(EnumDayWeek.getDayWeek(objDto.getDay()), objDto.getHour())) {
				list.add(new FieldMessage("Day/Hour", "Restaurant does not work on this day and time"));
			}
		}
		/** Verifica se o produto esta cadastrado na base **/
		objDto.getItens().stream().forEach(p -> {
			if (!productService.isProductExist(p.getIdProduct())) {
				list.add(new FieldMessage("Products", "Product id: " + p + " not found"));
			}
		});
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
