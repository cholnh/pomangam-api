package com.mrporter.pomangam.orderEntry.orderItem.repository;

import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Integer> {
}

