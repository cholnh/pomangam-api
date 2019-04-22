package com.mrporter.pomangam.promotionEntry.coupon.service;

import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;
import com.mrporter.pomangam.promotionEntry.coupon.repository.CouponJpaRepository;
import com.mrporter.pomangam.promotionEntry.coupon.repository.CouponRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {

    CouponRepositoryImpl couponRepository;
    CouponJpaRepository couponJpaRepository;

    @Override
    public int countCoupon(Integer customerIdx) throws Exception {
        return couponRepository.countCoupon(customerIdx);
    }

    @Override
    public CouponDto findByIdx(Integer idx) throws Exception {
        return couponRepository.findByIdx(idx);
    }

    @Override
    public List<CouponDto> findAll() throws Exception {
        return couponRepository.findAll();
    }

    @Override
    public List<CouponDto> findValidByCustomerIdx(Integer customer_idx) throws Exception {
        return couponRepository.findValidByCustomerIdx(customer_idx);
    }

    @Override
    public CouponDto getValidCouponByCode(String couponCode) throws Exception {
        return couponRepository.getValidCouponByCode(couponCode);
    }

    @Override
    public boolean isValid(String couponCode) throws Exception {
        return couponRepository.isValid(couponCode);
    }

    @Override
    public CouponDto save(CouponDto coupon) throws Exception {
        return null;
    }

    @Override
    public CouponDto update(Integer idx, CouponDto user) throws Exception {
        return null;
    }

    @Override
    public CouponDto patch(Integer idx, CouponDto user) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer idx) throws Exception {
        return null;
    }
}
