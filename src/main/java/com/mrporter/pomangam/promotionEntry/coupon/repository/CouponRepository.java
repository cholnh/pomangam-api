package com.mrporter.pomangam.promotionEntry.coupon.repository;

import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository {
    int countCoupon(@Param("customerIdx") Integer customerIdx) throws Exception;
    CouponDto findByIdx(Integer idx) throws Exception;
    List<CouponDto> findAll() throws Exception;
    List<CouponDto> findValidByCustomerId(String customer_id) throws Exception;
    CouponDto getValidCouponByCode(String couponCode) throws Exception;
    CouponDto getValidCouponByIdx(Integer couponIdx);
    boolean isValid(String couponCode) throws Exception;
    void useCoupon(Integer couponIdx, Integer customerIdx, Integer guestIdx, Integer orderIdx) throws Exception;

    CouponDto save(CouponDto coupon) throws Exception;
    CouponDto update(Integer idx, CouponDto user) throws Exception;
    CouponDto patch(Integer idx, CouponDto user) throws Exception;
    Boolean delete(Integer idx) throws Exception;
}
