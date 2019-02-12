package com.mrporter.pomangam.productEntry.product.repository;

import com.mrporter.pomangam.productEntry.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
}

