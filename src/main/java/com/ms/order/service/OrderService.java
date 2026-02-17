package com.ms.order.service;

import com.ms.order.dto.OrderRequestDTO;
import com.ms.order.dto.OrderResponseDTO;

public interface OrderService {

	OrderResponseDTO save(OrderRequestDTO orderRequest);
}
