package com.mrporter.pomangam.promotionEntry.promotion.repository;

import com.mrporter.pomangam.promotionEntry.promotion.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface PromotionJpaRepository extends JpaRepository<Promotion, Integer> {
}

