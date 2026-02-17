package com.ms.order.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ms.order.domain.model.OrderItem;
import com.ms.order.infrastructure.messaging.event.OrderItemCreatedEvent;

@Component
public class OrderItemCreatedEventMapper {

	public List<OrderItemCreatedEvent> toOrderItemCreatedEvent(List<OrderItem> items) {

		List<OrderItemCreatedEvent> orderItems = new ArrayList<OrderItemCreatedEvent>();

		for (OrderItem item : items) {

			OrderItemCreatedEvent orderItem = new OrderItemCreatedEvent();
			orderItem.setProductId(item.getProductId());
			orderItem.setQuantity(item.getQuantity());

			orderItems.add(orderItem);
		}

		return orderItems;
	}
}
