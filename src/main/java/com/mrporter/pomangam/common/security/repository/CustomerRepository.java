package com.mrporter.pomangam.common.security.repository;

import com.mrporter.pomangam.common.security.domain.CustomerTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CustomerRepository extends JpaRepository<CustomerTbl, Long> {
    public CustomerTbl findById(String id);
}
