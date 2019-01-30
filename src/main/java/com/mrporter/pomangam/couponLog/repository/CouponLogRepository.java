package com.mrporter.pomangam.couponLog.repository;

import com.mrporter.pomangam.couponLog.domain.CouponLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CouponLogRepository extends JpaRepository<CouponLog, Long> {
}
