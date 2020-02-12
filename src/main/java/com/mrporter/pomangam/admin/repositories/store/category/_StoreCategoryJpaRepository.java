package com.mrporter.pomangam.admin.repositories.store.category;

import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface _StoreCategoryJpaRepository extends JpaRepository<StoreCategory, Integer> {

}
