package com.br.cldelias.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.br.cldelias.enums.EnumDayWeek;
import com.br.cldelias.enums.EnumTypeOperation;

@Entity
public class OrderScheduling implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumDayWeek day;
	
	private LocalTime hour;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumTypeOperation type;
	
	@OneToMany(mappedBy="orderScheduling")
	private List<OrderSchedulingItem> itens;
	
	private OrderScheduling() {
		this.id = null;
		this.itens = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public EnumDayWeek getDay() {
		return day;
	}

	public void setDay(EnumDayWeek day) {
		this.day = day;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public EnumTypeOperation getType() {
		return type;
	}

	public void setType(EnumTypeOperation type) {
		this.type = type;
	}

	public List<OrderSchedulingItem> getItens() {
		return itens;
	}

	public void setItens(List<OrderSchedulingItem> itens) {
		this.itens = itens;
	}

	public void addItem(OrderSchedulingItem item) {
		if (item == null) {
			throw new IllegalArgumentException("item invalid");
		}
		this.getItens().add(item);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderScheduling other = (OrderScheduling) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class Builder {
		
		private OrderScheduling schedule;
		
		public Builder() {
			this.schedule = new OrderScheduling();
		}
		
		public Builder withClient(Client client) {
			this.schedule.setClient(client);
			return this;
		}
		
		public Builder withRestaurant(Restaurant restaurant) {
			this.schedule.setRestaurant(restaurant);
			return this;
		}
		
		public Builder withType(EnumTypeOperation type) {
			this.schedule.setType(type);
			return this;
		}
		
		public Builder atTheDay(EnumDayWeek day) {
			this.schedule.setDay(day);
			return this;
		}

		public Builder withHour(LocalTime hour) {
			this.schedule.setHour(hour);
			return this;
		}
	
		public OrderScheduling builder() {
			if (this.schedule.getClient() == null) {
				throw new IllegalArgumentException("client invalid");
			}
			if (this.schedule.getRestaurant() == null) {
				throw new IllegalArgumentException("restaurant invalid");
			}
			if (this.schedule.getDay() == null) {
				throw new IllegalArgumentException("day of schedule invalid");
			}
			if (this.schedule.getHour() == null) {
				throw new IllegalArgumentException("hour of schedule invalid");
			}
			if (this.schedule.getType() == null) {
				throw new IllegalArgumentException("type of schedule invald");
			}
			return schedule;
		}
	}
}
