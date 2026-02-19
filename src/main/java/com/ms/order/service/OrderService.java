package com.ms.order.service;

import com.ms.common.infrastructure.messaging.event.StockFailedEvent;
import com.ms.common.infrastructure.messaging.event.StockReservedEvent;
import com.ms.order.dto.OrderRequestDTO;
import com.ms.order.dto.OrderResponseDTO;

public interface OrderService {

	OrderResponseDTO save(OrderRequestDTO orderRequest);

	void confirmOrder(StockReservedEvent stockReservedEvent);

	void cancelOrder(StockFailedEvent stockFailedEvent);
}
