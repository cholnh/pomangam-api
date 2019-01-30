package com.mrporter.pomangam.product.repository;

import com.mrporter.pomangam.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ProductRepository extends JpaRepository<Product, Long> {
}

