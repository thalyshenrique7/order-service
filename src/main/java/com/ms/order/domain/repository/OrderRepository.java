package com.ms.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.order.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
