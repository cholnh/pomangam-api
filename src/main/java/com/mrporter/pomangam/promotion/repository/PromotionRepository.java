package com.mrporter.pomangam.promotion.repository;

import com.mrporter.pomangam.promotion.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}

