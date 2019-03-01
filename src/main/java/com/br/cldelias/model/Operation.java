package com.br.cldelias.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.br.cldelias.enums.EnumDayWeek;

@Entity
public class Operation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumDayWeek day;
	private LocalTime openingTime;
	private LocalTime closingTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumDayWeek getDay() {
		return day;
	}

	public void setDay(EnumDayWeek day) {
		this.day = day;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
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
		Operation other = (Operation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class Builder{
		
		private Operation operation;
		
		public Builder() {
			this.operation = new Operation();
		}
		
		public Builder atTheDay(EnumDayWeek day) {
			this.operation.setDay(day);
			return this;
		}
		
		public Builder openThe(LocalTime openingTime) {
			this.operation.setOpeningTime(openingTime);
			return this;
		}
		
		public Builder closed(LocalTime closingTime) {
			this.operation.setClosingTime(closingTime);
			return this;
		}
		
		public Operation build() {
			if (operation.getDay() == null) {
				throw new IllegalArgumentException("day of operation invalid");
			}
			if (operation.getOpeningTime() == null) {
				throw new IllegalArgumentException("opening time of operation invalid");
			}
			if (operation.getClosingTime() == null) {
				throw new IllegalArgumentException("closing time of operation invalid");
			}
			return this.operation;
		}

	}
	
}
