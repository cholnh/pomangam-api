package com.mrporter.pomangam.coupon.repository;

import com.mrporter.pomangam.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
