package com.mrporter.pomangam.orderEntry.customer.repository;

import com.mrporter.pomangam.orderEntry.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {
    Customer findById(String id);
}
