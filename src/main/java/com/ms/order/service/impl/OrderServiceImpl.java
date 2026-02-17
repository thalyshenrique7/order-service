package com.ms.order.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
