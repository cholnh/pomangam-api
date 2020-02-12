package com.mrporter.pomangam.client.repositories.store.category;

import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StoreCategoryJpaRepository extends JpaRepository<StoreCategory, Integer> {

}
