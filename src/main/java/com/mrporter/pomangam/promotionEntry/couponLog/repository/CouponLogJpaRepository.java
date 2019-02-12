package com.mrporter.pomangam.promotionEntry.couponLog.repository;

import com.mrporter.pomangam.promotionEntry.couponLog.domain.CouponLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CouponLogJpaRepository extends JpaRepository<CouponLog, Integer> {
}
