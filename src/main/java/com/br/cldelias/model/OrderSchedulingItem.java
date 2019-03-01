package com.br.cldelias.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderSchedulingItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="order_scheduling_id")
	private OrderScheduling orderScheduling;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	private Double quantity;
	private Double price;
	
	private OrderSchedulingItem() {
		this.id = null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public OrderScheduling getOrderScheduling() {
		return orderScheduling;
	}

	public void setOrderScheduling(OrderScheduling orderScheduling) {
		this.orderScheduling = orderScheduling;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderSchedulingItem other = (OrderSchedulingItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class Builder {
		
		private OrderSchedulingItem scheduleItem;
		
		public Builder() {
			this.scheduleItem = new OrderSchedulingItem();
		}
		
		public Builder withOrderScheduling(OrderScheduling orderScheduling) {
			this.scheduleItem.setOrderScheduling(orderScheduling);
			return this;
		}
		
		public Builder withProduct(Product product) {
			this.scheduleItem.setProduct(product);
			return this;
		}
		
		public Builder withQuantify(Double quantify) {
			this.scheduleItem.setQuantity(quantify);
			return this;
		}
		
		public Builder withPrice(Double price) {
			this.scheduleItem.setPrice(price);
			return this;
		}
		
		public OrderSchedulingItem builder() {
			if (this.scheduleItem.getOrderScheduling() == null) {
				throw new IllegalArgumentException("orderScheduling invalid");
			}
			if (this.scheduleItem.getProduct() == null) {
				throw new IllegalArgumentException("product invalid");
			}
			if (this.scheduleItem.getQuantity() == null || this.scheduleItem.getQuantity() <= 0) {
				throw new IllegalArgumentException("quantify invalid");
			}
			if (this.scheduleItem.getPrice() == null || this.scheduleItem.getPrice() <= 0) {
				throw new IllegalArgumentException("quantify invalid");
			}
			return scheduleItem;
		}
	}
}
