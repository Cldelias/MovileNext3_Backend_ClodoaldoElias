package com.br.cldelias.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.br.cldelias.enums.EnumDayWeek;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_operation",
		joinColumns = @JoinColumn(name = "restaurant_id"),
		inverseJoinColumns = @JoinColumn(name = "operation_id")
	)
	private List<Operation> operations;
	
	public Restaurant() {
		this.id = null;
		this.operations = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public boolean isOperations(EnumDayWeek day, LocalTime hour) {
		Optional<Operation> op = this.operations
		.stream()
		.filter(p -> p.getDay().getDayWeek() == day.getDayWeek() 
		&& p.getOpeningTime().isBefore(hour) && p.getClosingTime().isAfter(hour)).findFirst();
		return op.isPresent();
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
		Restaurant other = (Restaurant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class Builder{
		
		private Restaurant restaurant;
		
		public Builder() {
			this.restaurant = new Restaurant();
		}
		
		public Builder withName(String name) {
			this.restaurant.setName(name);
			return this;
		}
		
		public Builder addOperation(Operation operation) {
			if (operation == null) {
				throw new IllegalArgumentException("operation invalid");
			}
			this.restaurant.getOperations().add(operation);
			return this;
		}
		
		public Builder addListOperation(List<Operation> operations) {
			if (operations == null || operations.isEmpty()) {
				throw new IllegalArgumentException("operation invalid");
			}
			this.restaurant.getOperations().addAll(operations);
			return this;
		}

		
		public Restaurant build() {
			if (this.restaurant.getName() == null || this.restaurant.getName().isEmpty()) {
				throw new IllegalArgumentException("name of restaurant invalid");
			}
			if (this.restaurant.getOperations() == null || this.restaurant.getOperations().isEmpty()) {
				throw new IllegalArgumentException("restaurant without set operation");
			}
			return this.restaurant;
		}
	}

}
