package com.br.cldelias.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.cldelias.model.OrderScheduling;
import com.br.cldelias.services.OrderSchedulingService;

import br.com.cldelias.dto.OrderSchedulingDTO;
import br.com.cldelias.dto.OrderSchedulingNewDTO;

@RestController
@RequestMapping(value="/schedules")
public class OrderSchedulingResource {
	
	
	@Autowired
	private OrderSchedulingService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OrderSchedulingNewDTO objDto) {
		OrderScheduling entifty = this.service.fromDTO(objDto);
		entifty = this.service.insertScheling(entifty);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(entifty.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OrderSchedulingDTO> findById(@PathVariable Integer id) {
		OrderSchedulingDTO Dto = this.service.findById(id);
		return ResponseEntity.ok(Dto);
	}
	
	@RequestMapping(value="/type/{typeOperation}", method=RequestMethod.GET)
	public ResponseEntity<List<OrderSchedulingDTO>> findByTypeOperation(@PathVariable Integer typeOperation) {
		List<OrderSchedulingDTO> listDto = this.service.findByTypeOperationDTO(typeOperation);
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.GET)
	public ResponseEntity<List<OrderSchedulingDTO>> findByIdClient(@PathVariable Integer idClient) {
		List<OrderSchedulingDTO> listDto = this.service.findByIdClient(idClient);
		return ResponseEntity.ok().body(listDto);
	}


}
