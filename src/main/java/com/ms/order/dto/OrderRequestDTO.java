package com.ms.order.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {

	private Long id;

	private List<OrderItemDTO> items;

	private BigDecimal totalAmount;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public List<OrderItemDTO> getItems() {

		return items;
	}

	public void setItems(List<OrderItemDTO> items) {

		this.items = items;
	}

	public BigDecimal getTotalAmount() {

		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {

		this.totalAmount = totalAmount;
	}

}
