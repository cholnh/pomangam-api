package com.mrporter.pomangam.pointLog.repository;

import com.mrporter.pomangam.pointLog.domain.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface PointLogRepository extends JpaRepository<PointLog, Long> {
}

