package com.br.cldelias.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	private Product() {
		this.id = null;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class Builder {

		private Product product;

		public Builder() {
			product = new Product();
		}

		public Builder withName(String name) {
			this.product.setName(name);
			return this;
		}

		public Builder withPrice(Double price) {
			this.product.setPrice(price);
			return this;
		}

		public Builder withCategory(Category category) {
			this.product.setCategory(category);
			return this;
		}

		public Product build() throws Exception {
			if (this.product.getName() == null || this.product.getName().isEmpty()) {
				throw new IllegalArgumentException("name of product invalid");
			}
			if (this.product.getPrice() == null || this.product.getPrice() <= 0) {
				throw new IllegalArgumentException("price of product invalid");
			}
			if (this.product.getCategory() == null) {
				throw new IllegalArgumentException("category of product invalid");
			}
			return product;
		}
	}
}
