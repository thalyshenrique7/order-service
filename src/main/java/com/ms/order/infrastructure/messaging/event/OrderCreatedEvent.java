package com.ms.order.infrastructure.messaging.event;

import java.util.List;

public class OrderCreatedEvent {

	private Long orderId;

	private List<OrderItemCreatedEvent> items;

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public List<OrderItemCreatedEvent> getItems() {

		return items;
	}

	public void setItems(List<OrderItemCreatedEvent> items) {

		this.items = items;
	}

}
