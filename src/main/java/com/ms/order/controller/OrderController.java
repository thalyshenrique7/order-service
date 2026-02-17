package com.ms.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.order.dto.OrderRequestDTO;
import com.ms.order.dto.OrderResponseDTO;
import com.ms.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponseDTO> placeOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {

		OrderResponseDTO orderResponse = this.orderService.save(orderRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}
}
