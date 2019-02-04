package com.mrporter.pomangam.order.repository;

import com.mrporter.pomangam.common.map.domain.CommonMap;
import com.mrporter.pomangam.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderRepository extends JpaRepository<Order, Long> {
}

