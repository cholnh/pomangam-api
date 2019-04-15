package com.mrporter.pomangam.deliveryEntry.regionCategory.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class RegionCategoryRepositoryImpl implements RegionCategoryRepository {
    @PersistenceContext
    EntityManager em;

}
