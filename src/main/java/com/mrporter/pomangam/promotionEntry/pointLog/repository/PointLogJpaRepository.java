package com.mrporter.pomangam.promotionEntry.pointLog.repository;

import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface PointLogJpaRepository extends JpaRepository<PointLog, Integer> {
}

