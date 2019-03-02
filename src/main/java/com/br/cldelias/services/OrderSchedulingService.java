package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.enums.EnumDayWeek;
import com.br.cldelias.enums.EnumPatternFormatDate;
import com.br.cldelias.enums.EnumTypeOperation;
import com.br.cldelias.model.Client;
import com.br.cldelias.model.OrderScheduling;
import com.br.cldelias.model.OrderSchedulingItem;
import com.br.cldelias.model.Restaurant;
import com.br.cldelias.repositories.OrderSchedulingRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;
import com.br.cldelias.services.exceptions.ValidationFieldException;

import br.com.cldelias.dto.OrderSchedulingDTO;
import br.com.cldelias.dto.OrderSchedulingNewDTO;
import br.com.cldelias.utils.DateUtil;

@Service
public class OrderSchedulingService {
	
	@Autowired
	private OrderSchedulingRepository schedulingRepo;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderSchedulingItemService orderSchedulingItemService;
	
	public OrderScheduling find(Integer id) {
		Optional<OrderScheduling> op = this.schedulingRepo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"OrderScheduling not found! Id: " + id + ", Type: " + OrderScheduling.class.getName()));		
	}

	public OrderScheduling save(OrderScheduling entity) {
		return this.schedulingRepo.save(entity);
	}
	
	public OrderScheduling insertScheling(OrderScheduling entity) {
		OrderScheduling obj = this.schedulingRepo.save(entity);
		if (obj != null) {
			obj.setItens(this.orderSchedulingItemService.saveAll(entity.getItens()));
		}
		return obj;
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.schedulingRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a OrderScheduling");
		}
	}
	
	public List<OrderScheduling> findByTypeOperation(Integer type) {
		return this.schedulingRepo.findByType(EnumTypeOperation.getTypeOperation(type));
	}
	
	public List<OrderSchedulingDTO> findByTypeOperationDTO(Integer type) {
		List<OrderScheduling> list = this.findByTypeOperation(type);
		this.checkListOrderScheduling(list, "typeOperation:" + type);
		return this.getOrderSchedulingDTO(list);
	}
	
	public List<OrderSchedulingDTO> findByIdClient(Integer idClient) {
		List<OrderScheduling> list = this.schedulingRepo.findByIdClient(idClient);
		this.checkListOrderScheduling(list, "idClient:" + idClient);
		return this.getOrderSchedulingDTO(list);
	}

	public OrderSchedulingDTO findById(Integer id) {
		OrderScheduling entity = this.find(id);
		return this.getOrderSchedulingDTO(entity);
	}
	
	private OrderSchedulingDTO getOrderSchedulingDTO(OrderScheduling entity) {
		OrderSchedulingDTO Dto = new OrderSchedulingDTO.Builder()
						.withNameClient(entity.getClient().getName())
						.withEmailClient(entity.getClient().getEmail())
						.withDay(entity.getDay().toString())
						.withNameRestaurant(entity.getRestaurant().getName())
						.withHour(DateUtil.formatLocalTime(entity.getHour(), EnumPatternFormatDate.FORMAT_02))
						.withType(entity.getType().getDescription())
						.addListItens(orderSchedulingItemService.getOrderSchedulingItemDTO(entity.getItens()))
						.builder();
		return Dto;
	}
	
	private List<OrderSchedulingDTO> getOrderSchedulingDTO(List<OrderScheduling> list) {
		List<OrderSchedulingDTO> listDto = list.stream()
				.map(c -> new OrderSchedulingDTO.Builder()
						.withNameClient(c.getClient().getName())
						.withEmailClient(c.getClient().getEmail())
						.withDay(c.getDay().toString())
						.withNameRestaurant(c.getRestaurant().getName())
						.withHour(DateUtil.formatLocalTime(c.getHour(), EnumPatternFormatDate.FORMAT_02))
						.withType(c.getType().getDescription())
						.addListItens(orderSchedulingItemService.getOrderSchedulingItemDTO(c.getItens()))
						.builder()).collect(Collectors.toList());
		return listDto;
	}
 
	public OrderScheduling fromDTO(@Valid OrderSchedulingNewDTO objDto) {
		Client client = this.clientService.find(objDto.getIdClient());
		Restaurant restaurant = this.restaurantService.find(objDto.getIdRestaurant());
		OrderScheduling orderScheduling = new OrderScheduling
				.Builder()
				.withClient(client)
				.withRestaurant(restaurant)
				.atTheDay(EnumDayWeek.getDayWeek(objDto.getDay()))
				.withHour(DateUtil.parseLocalTime(objDto.getHour(), EnumPatternFormatDate.FORMAT_02))
				.withType(EnumTypeOperation.getTypeOperation(objDto.getType()))
				.builder();
		
		List<OrderSchedulingItem> itens = objDto.getItens().stream().map(c -> {
			OrderSchedulingItem orderSchedulingItem = new OrderSchedulingItem
					.Builder()
					.withProduct(productService.find(c.getIdProduct()))
					.withQuantify(c.getQuantify())
					.withPrice(c.getPrice())
					.withOrderScheduling(orderScheduling)
					.builder();
			return orderSchedulingItem;
		}).collect(Collectors.toList());
		
		if (itens == null || itens.isEmpty()) {
			throw new ValidationFieldException("Itens of scheduling not found");
		}

		orderScheduling.setItens(itens);
		return orderScheduling;
	}
	
	private void checkListOrderScheduling(List<OrderScheduling> list, String message) {
		if (list == null || list.isEmpty()) {
			throw new ObjectNotFoundException("OrderScheduling not found! " + message + " Type: " + OrderScheduling.class.getName());			
		}
	}


}
