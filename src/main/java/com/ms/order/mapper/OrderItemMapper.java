package com.ms.order.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ms.order.domain.model.OrderItem;
import com.ms.order.dto.OrderItemDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderItemMapper extends OrderItem implements Serializable {

	private static final long serialVersionUID = 985098201651404172L;

	public OrderItemMapper() {

		super(OrderItem.class);
	}

	public abstract OrderItemDTO toOrderItemDto(OrderItem entity);

	public abstract List<OrderItemDTO> toOrderItemDtos(List<OrderItem> entity);

}
