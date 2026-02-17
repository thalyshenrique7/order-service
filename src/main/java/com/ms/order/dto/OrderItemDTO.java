package com.ms.order.dto;

import java.math.BigDecimal;

public class OrderItemDTO {

	private BigDecimal productId;
	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private BigDecimal totalAmount;

	public BigDecimal getProductId() {

		return productId;
	}

	public void setProductId(BigDecimal productId) {

		this.productId = productId;
	}

	public BigDecimal getQuantity() {

		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {

		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {

		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalAmount() {

		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {

		this.totalAmount = totalAmount;
	}

}
