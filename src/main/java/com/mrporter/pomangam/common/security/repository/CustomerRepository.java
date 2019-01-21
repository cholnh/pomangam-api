package com.mrporter.pomangam.common.security.repository;

import com.mrporter.pomangam.common.security.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findById(String id);
}
