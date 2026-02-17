package com.ms.order.infrastructure.messaging.event;

import java.math.BigDecimal;

public class OrderCreatedEvent {

	private Long orderId;
	private Long productId;

	private BigDecimal quantity;

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

	public BigDecimal getQuantity() {

		if (quantity == null)
			return BigDecimal.ZERO;

		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;
	}
}
