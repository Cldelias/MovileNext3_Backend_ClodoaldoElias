package br.com.cldelias.dto;

import java.time.LocalTime;
import java.util.List;

public class OrderSchedulingDTO {
	
	private String nameClient;
	private String emailClient;
	private String nameRestaurant;
	private String day;
	private LocalTime hour;
	private String type;
	private List<OrderSchedulingItemDTO> itensDTO;
	
	public OrderSchedulingDTO() {
		
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public String getNameRestaurant() {
		return nameRestaurant;
	}

	public void setNameRestaurant(String nameRestaurant) {
		this.nameRestaurant = nameRestaurant;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<OrderSchedulingItemDTO> getItensDTO() {
		return itensDTO;
	}

	public void setItensDTO(List<OrderSchedulingItemDTO> itensDTO) {
		this.itensDTO = itensDTO;
	}


	public static class Builder  {
		
		private OrderSchedulingDTO objDto;
		
		public Builder() {
			this.objDto = new OrderSchedulingDTO();
		}
		
		public Builder withNameClient(String nameClient) {
			this.objDto.setNameClient(nameClient);
			return this;
		}
		
		public Builder withEmailClient(String emailClient) {
			this.objDto.setEmailClient(emailClient);
			return this;
		}
		
		public Builder withNameRestaurant(String nameRestaurant) {
			this.objDto.setNameRestaurant(nameRestaurant);
			return this;
		}
		
		public Builder withDay(String day) {
			this.objDto.setDay(day);
			return this;
		}
		
		public Builder withHour(LocalTime hour) {
			this.objDto.setHour(hour);
			return this;
		}

		public Builder withType(String type) {
			this.objDto.setType(type);
			return this;
		}
		
		public Builder addListItens(List<OrderSchedulingItemDTO> itensDTO) {
			this.objDto.setItensDTO(itensDTO);
			return this;
		}

		public OrderSchedulingDTO builder() {
			return this.objDto;
		}
		
	}


}
