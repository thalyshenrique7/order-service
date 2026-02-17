package com.ms.order.mapper;

import java.io.Serializable;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ms.order.domain.model.Order;
import com.ms.order.dto.OrderRequestDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderMapper extends Order implements Serializable {

	private static final long serialVersionUID = 985098201651404172L;

	public OrderMapper() {

		super(Order.class);
	}

	public abstract Order toEntity(OrderRequestDTO orderRequest);

}
