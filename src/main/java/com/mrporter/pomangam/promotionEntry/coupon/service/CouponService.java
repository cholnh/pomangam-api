package com.mrporter.pomangam.promotionEntry.coupon.service;

import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;

import java.util.List;

public interface CouponService {
    int countCoupon(Integer customerIdx) throws Exception;
    CouponDto findByIdx(Integer idx) throws Exception;
    List<CouponDto> findAll() throws Exception;
    List<CouponDto> findValidByCustomerIdx(Integer customer_idx) throws Exception;
    CouponDto getValidCouponByCode(String couponCode) throws Exception;
    boolean isValid(String couponCode) throws Exception;
    //void useCoupon(Integer couponIdx, Integer customerIdx, Integer orderIdx) throws Exception;

    CouponDto save(CouponDto coupon) throws Exception;
    CouponDto update(Integer idx, CouponDto user) throws Exception;
    CouponDto patch(Integer idx, CouponDto user) throws Exception;
    Boolean delete(Integer idx) throws Exception;
}
