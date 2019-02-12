package com.mrporter.pomangam.orderEntry.order.repository;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
}

