package com.ms.order.service.impl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.common.infrastructure.messaging.event.StockFailedEvent;
import com.ms.common.infrastructure.messaging.event.StockReservedEvent;
import com.ms.order.domain.model.Order;
import com.ms.order.domain.repository.OrderRepository;
import com.ms.order.dto.OrderRequestDTO;
import com.ms.order.dto.OrderResponseDTO;
import com.ms.order.enums.OrderStatus;
import com.ms.order.infrastructure.messaging.producer.OrderProducer;
import com.ms.order.mapper.OrderMapper;
import com.ms.order.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final static String DESCRIPTION_ORDER_CREATED = "Pedido criado e aguardando confirmação de estoque.";

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderProducer orderProducer;

	@Override
	public OrderResponseDTO save(OrderRequestDTO orderRequest) {

		Order order = this.orderMapper.toEntity(orderRequest);
		order.setStatus(OrderStatus.PENDING_STOCK);

		order.getItems().stream().forEach(item -> {
			item.setOrder(order);
		});

		try {

			this.orderRepository.save(order);

		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um problema ao tentar efetuar o pedido.");
		}

		this.orderProducer.publishOrderCreatedEvent(order);

		OrderResponseDTO orderResponse = new OrderResponseDTO(order.getId(), order.getStatus(),
				DESCRIPTION_ORDER_CREATED, Calendar.getInstance());

		return orderResponse;
	}

	@Override
	public void confirmOrder(StockReservedEvent stockReservedEvent) {

		Order order = null;

		Long orderId = stockReservedEvent.getOrderId();

		try {

			order = this.orderRepository.getReferenceById(orderId);

		} catch (Exception e) {

			LOGGER.error("Falha ao processar evento stock.reserved | orderId={}", orderId, e);
			throw e;
		}

		order.setStatus(OrderStatus.CONFIRMED);

		this.orderRepository.save(order);

		LOGGER.info("Pedido confirmado após reserva de estoque | orderId={}", orderId);
	}

	@Override
	public void cancelOrder(StockFailedEvent stockFailedEvent) {

		Order order = null;

		Long orderId = stockFailedEvent.getOrderId();

		try {

			order = this.orderRepository.getReferenceById(orderId);

		} catch (Exception e) {

			LOGGER.error("Falha ao processar evento stock.failed | orderId={}", orderId, e);
			throw e;
		}

		order.setStatus(OrderStatus.CANCELLED);

		this.orderRepository.save(order);

		LOGGER.info("Pedido cancelado por falha de estoque | orderId={}", orderId);
	}

}
