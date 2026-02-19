package com.ms.order.infrastructure.messaging.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.common.infrastructure.messaging.event.StockFailedEvent;
import com.ms.common.infrastructure.messaging.event.StockReservedEvent;
import com.ms.order.service.OrderService;

@Component
public class OrderConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

	@Autowired
	private OrderService orderService;

	@RabbitListener(queues = "${broker.queue.order.stock.reserved}", containerFactory = "orderListenerContainerFactory")
	public void listenStockReserved(@Payload StockReservedEvent stockReservedEvent) {

		LOGGER.info("Evento recebido: stock.reserved | orderId={}", stockReservedEvent.getOrderId());

		this.orderService.confirmOrder(stockReservedEvent);
	}

	@RabbitListener(queues = "${broker.queue.order.stock.failed}", containerFactory = "orderListenerContainerFactory")
	public void listenStockFailed(@Payload StockFailedEvent stockFailedEvent) {

		LOGGER.warn("Evento recebido: stock.failed | orderId={}", stockFailedEvent.getOrderId());

		this.orderService.cancelOrder(stockFailedEvent);
	}
}
