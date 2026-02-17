package com.ms.order.infrastructure.messaging.producer;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.order.domain.model.Order;
import com.ms.order.dto.OrderItemDTO;
import com.ms.order.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.order.mapper.OrderItemMapper;

@Component
public class OrderProducer {

	final RabbitTemplate rabbitTemplate;

	final OrderItemMapper orderItemMapper;

	public OrderProducer(RabbitTemplate rabbitTemplate, OrderItemMapper orderItemMapper) {

		this.rabbitTemplate = rabbitTemplate;
		this.orderItemMapper = orderItemMapper;
	}

	@Value("${broker.exchange.order}")
	private String exchange;

	@Value("${broker.routing.order-created}")
	private String routingKey;

	public void publishOrderCreatedEvent(Order order) {

		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
		orderCreatedEvent.setOrderId(order.getId());

		List<OrderItemDTO> items = this.orderItemMapper.toOrderItemDtos(order.getItems());
		orderCreatedEvent.setItems(items);

		this.rabbitTemplate.convertAndSend(exchange, routingKey, orderCreatedEvent);
	}
}
