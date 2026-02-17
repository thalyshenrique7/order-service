package com.ms.order.dto;

import java.util.Calendar;

import com.ms.order.enums.OrderStatus;

public class OrderResponseDTO {

	private Long orderId;

	private OrderStatus status;
	private String message;

	private Calendar createdAt;

	public OrderResponseDTO(Long orderId, OrderStatus status, String message, Calendar createdAt) {

		this.orderId = orderId;
		this.status = status;
		this.message = message;
		this.createdAt = createdAt;
	}

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public OrderStatus getStatus() {

		return status;
	}

	public void setStatus(OrderStatus status) {

		this.status = status;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Calendar getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {

		this.createdAt = createdAt;
	}

}
