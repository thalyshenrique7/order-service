package com.ms.order.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.ms.order.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985098201651404172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal quantity = BigDecimal.ZERO;

	@Column(name = "total_amount")
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column(name = "created_at")
	private Calendar createdAt;

	@Column(name = "updated_at")
	private Calendar updatedAt;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "customer_id")
	private Long customerId;

	public Order(Class<Order> classOrder) {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public BigDecimal getQuantity() {

		if (quantity == null)
			return BigDecimal.ZERO;

		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;
	}

	public BigDecimal getTotalAmount() {

		if (totalAmount == null)
			return BigDecimal.ZERO;

		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {

		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {

		return status;
	}

	public void setStatus(OrderStatus status) {

		this.status = status;
	}

	public Calendar getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {

		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {

		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {

		this.updatedAt = updatedAt;
	}

	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

}
