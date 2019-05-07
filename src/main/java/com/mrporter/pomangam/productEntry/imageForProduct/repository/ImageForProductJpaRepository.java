package com.mrporter.pomangam.productEntry.imageForProduct.repository;

import com.mrporter.pomangam.productEntry.imageForProduct.domain.ImageForProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ImageForProductJpaRepository extends JpaRepository<ImageForProduct, Integer> {
}

