package com.mrporter.pomangam.cost.repository;

import com.mrporter.pomangam.common.security.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CostRepository extends JpaRepository<Customer, Long> {
}
