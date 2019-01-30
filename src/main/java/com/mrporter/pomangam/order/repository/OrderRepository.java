package com.mrporter.pomangam.order.repository;

import com.mrporter.pomangam.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderRepository extends JpaRepository<Order, Long> {
}

