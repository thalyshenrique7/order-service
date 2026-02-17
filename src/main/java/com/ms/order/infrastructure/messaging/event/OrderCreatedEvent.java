package com.ms.order.infrastructure.messaging.event;

import java.util.List;

import com.ms.order.dto.OrderItemDTO;

public class OrderCreatedEvent {

	private Long orderId;

	private List<OrderItemDTO> items;

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public List<OrderItemDTO> getItems() {

		return items;
	}

	public void setItems(List<OrderItemDTO> items) {

		this.items = items;
	}

}
