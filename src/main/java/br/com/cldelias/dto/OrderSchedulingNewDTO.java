package br.com.cldelias.dto;

import java.time.LocalTime;
import java.util.List;

public class OrderSchedulingNewDTO {
	
	private Integer idClient;
	private Integer idRestaurant;
	private Integer day;
	private LocalTime hour;
	private Integer type;
	private List<OrderSchedulingItemNewDTO> itens;
	
	public OrderSchedulingNewDTO() {
		
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Integer idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<OrderSchedulingItemNewDTO> getItens() {
		return itens;
	}

	public void setItens(List<OrderSchedulingItemNewDTO> itens) {
		this.itens = itens;
	}

}
