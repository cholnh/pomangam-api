package com.mrporter.pomangam.productEntry.cost.repository;

import com.mrporter.pomangam.orderEntry.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CostJpaRepository extends JpaRepository<Customer, Integer> {
}