package br.com.cldelias.dto;

import java.io.Serializable;

public class OrderSchedulingItemNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idProduct;
	private Double quantify;
	private Double price;
	
	public OrderSchedulingItemNewDTO() {
		
	}
	
	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Double getQuantify() {
		return quantify;
	}

	public void setQuantify(Double quantify) {
		this.quantify = quantify;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
