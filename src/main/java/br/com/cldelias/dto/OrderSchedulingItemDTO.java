package br.com.cldelias.dto;

public class OrderSchedulingItemDTO {
	
	private Integer idProduct;
	private String nameProduct;
	private Double quantify;
	private Double price;
	private Double amount;
	
	public OrderSchedulingItemDTO() {
		
	}
	
	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
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
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public static class Builder  {
		
		private OrderSchedulingItemDTO objDto;
		
		public Builder() {
			this.objDto = new OrderSchedulingItemDTO();
		}
		
		public Builder withProduct(Integer idProduct) {
			this.objDto.setIdProduct(idProduct);
			return this;
		}
		
		public Builder withName(String nameProduct) {
			this.objDto.setNameProduct(nameProduct);
			return this;
		}
		
		public Builder withQuantify(Double quantify) {
			this.objDto.setQuantify(quantify);
			return this;
		}
		
		public Builder withPrice(Double price) {
			this.objDto.setPrice(price);
			return this;
		}
		
		public OrderSchedulingItemDTO builder() {
			if (this.objDto.getQuantify() > 0 && this.objDto.getPrice() > 0) {
				this.objDto.setAmount(this.objDto.getQuantify() * this.objDto.getPrice());
			}
			return this.objDto;
		}
		
	}


}
