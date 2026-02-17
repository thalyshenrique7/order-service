package com.ms.order.infrastructure.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.order.domain.model.Order;
import com.ms.order.infrastructure.messaging.event.OrderCreatedEvent;

@Component
public class OrderProducer {

	final RabbitTemplate rabbitTemplate;

	public OrderProducer(RabbitTemplate rabbitTemplate) {

		this.rabbitTemplate = rabbitTemplate;
	}

	@Value(value = "${broker.queue.stock.name}")
	private String routingKey;

	public void publishMessageStock(Order order) {

		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
		orderCreatedEvent.setOrderId(order.getId());
		orderCreatedEvent.setProductId(order.getProductId());
		orderCreatedEvent.setQuantity(order.getQuantity());

		this.rabbitTemplate.convertAndSend("", routingKey, orderCreatedEvent);
	}
}
