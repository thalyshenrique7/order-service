package com.ms.order.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985098201651404172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	private BigDecimal quantity = BigDecimal.ZERO;

	private BigDecimal unitPrice = BigDecimal.ZERO;

	private BigDecimal totalAmount = BigDecimal.ZERO;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	public OrderItem(Class<OrderItem> orderItemClass) {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

	public BigDecimal getQuantity() {

		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {

		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {

		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {

		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalAmount() {

		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {

		this.totalAmount = totalAmount;
	}

	public Order getOrder() {

		return order;
	}

	public void setOrder(Order order) {

		this.order = order;
	}

}
