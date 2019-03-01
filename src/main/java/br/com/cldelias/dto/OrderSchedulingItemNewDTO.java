package br.com.cldelias.dto;

public class OrderSchedulingItemNewDTO {
	
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

	public static class Builder  {
		
		private OrderSchedulingItemNewDTO objDto;
		
		public Builder() {
			this.objDto = new OrderSchedulingItemNewDTO();
		}
		
		public Builder withProduct(Integer idProduct) {
			this.objDto.setIdProduct(idProduct);
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
		
		public OrderSchedulingItemNewDTO builder() {
			return this.objDto;
		}
		
	}


}
