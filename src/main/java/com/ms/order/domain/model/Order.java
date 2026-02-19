package com.ms.order.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ms.order.enums.OrderStatus;
import com.ms.order.utils.BigDecimalUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@Column(name = "total_amount_items")
	private BigDecimal totalAmountItems = BigDecimal.ZERO;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items;

	public Order() {

	}

	public Order(Class<Order> classOrder) {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public BigDecimal getTotalAmountItems() {

		return BigDecimalUtils.zeroIfNull(totalAmountItems);
	}

	public void setTotalAmountItems(BigDecimal totalAmountItems) {

		this.totalAmountItems = totalAmountItems;
	}

	public OrderStatus getStatus() {

		return status;
	}

	public void setStatus(OrderStatus status) {

		this.status = status;
	}

	public List<OrderItem> getItems() {

		if (items == null)
			return new ArrayList<OrderItem>();

		return items;
	}

	public void setItems(List<OrderItem> items) {

		this.items = items;
	}

}
