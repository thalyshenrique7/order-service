package com.ms.order.infrastructure.messaging.producer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.common.infrastructure.messaging.event.OrderCreatedEvent;
import com.ms.common.infrastructure.messaging.event.OrderItemCreatedEvent;
import com.ms.order.domain.model.Order;
import com.ms.order.mapper.OrderItemCreatedEventMapper;

@Component
public class OrderProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

	final RabbitTemplate rabbitTemplate;

	final OrderItemCreatedEventMapper orderItemCreatedEventMapper;

	public OrderProducer(RabbitTemplate rabbitTemplate, OrderItemCreatedEventMapper orderItemCreatedEventMapper) {

		this.rabbitTemplate = rabbitTemplate;
		this.orderItemCreatedEventMapper = orderItemCreatedEventMapper;
	}

	@Value("${broker.exchange.order}")
	private String exchange;

	@Value("${broker.routing.order-created}")
	private String routingKey;

	public void publishOrderCreatedEvent(Order order) {

		Long orderId = order.getId();

		LOGGER.info("Publicando evento: order.created | exchange={} routingKey={} orderId={}", exchange, routingKey,
				orderId);

		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
		orderCreatedEvent.setOrderId(orderId);

		List<OrderItemCreatedEvent> items = this.orderItemCreatedEventMapper.toOrderItemCreatedEvent(order.getItems());
		orderCreatedEvent.setItems(items);

		this.rabbitTemplate.convertAndSend(exchange, routingKey, orderCreatedEvent);

		LOGGER.info("Evento publicado: order.created | orderId={}", orderId);
	}
}
