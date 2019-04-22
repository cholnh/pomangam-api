package com.mrporter.pomangam.orderEntry.orderLog.repository;

import com.mrporter.pomangam.orderEntry.orderLog.domain.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderLogJpaRepository extends JpaRepository<OrderLog, Integer> {
}

