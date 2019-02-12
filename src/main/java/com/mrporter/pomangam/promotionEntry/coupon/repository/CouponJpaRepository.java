package com.mrporter.pomangam.promotionEntry.coupon.repository;

import com.mrporter.pomangam.promotionEntry.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CouponJpaRepository extends JpaRepository<Coupon, Integer> {
}
