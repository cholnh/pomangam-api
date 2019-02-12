package com.mrporter.pomangam.deliveryEntry.regionCategory.repository;

import com.mrporter.pomangam.deliveryEntry.regionCategory.domain.RegionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface RegionCategoryJpaRepository extends JpaRepository<RegionCategory, Integer> {
}
