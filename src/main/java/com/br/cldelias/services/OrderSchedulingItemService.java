package com.br.cldelias.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.cldelias.model.OrderSchedulingItem;
import com.br.cldelias.repositories.OrderSchedulingItemRepository;
import com.br.cldelias.services.exceptions.DataIntegrityException;
import com.br.cldelias.services.exceptions.ObjectNotFoundException;

import br.com.cldelias.dto.OrderSchedulingItemDTO;

@Service
public class OrderSchedulingItemService {
	
	@Autowired
	private OrderSchedulingItemRepository schedulingRepo;
	
	public OrderSchedulingItem find(Integer id) {
		Optional<OrderSchedulingItem> op = this.schedulingRepo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException(
				"OrderSchedulingItem not found! Id: " + id + ", Type: " + OrderSchedulingItem.class.getName()));		
	}

	public OrderSchedulingItem save(OrderSchedulingItem entity) {
		return this.schedulingRepo.save(entity);
	}
	
	public List<OrderSchedulingItem> saveAll(List<OrderSchedulingItem> entity) {
		return this.schedulingRepo.saveAll(entity);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.schedulingRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a OrderScheduling");
		}
	}
	
	public List<OrderSchedulingItemDTO> getOrderSchedulingItemDTO(List<OrderSchedulingItem> list) {
		List<OrderSchedulingItemDTO> listItemDto = list.stream()
				.map(c -> new OrderSchedulingItemDTO.Builder()
						.withProduct(c.getProduct().getId())
						.withName(c.getProduct().getName())
						.withPrice(c.getPrice())
						.withQuantify(c.getQuantity())
						.builder()).collect(Collectors.toList());
		return listItemDto;
	}


}
