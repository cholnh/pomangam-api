package com.mrporter.pomangam.client.services.user.coupon;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.coupon.CouponDto;

import java.util.List;

public interface CouponService {
    List<CouponDto> findAllByIdxUser(Long uIdx);
    CouponDto findOneByIdx(Long cIdx);
    CouponDto findOneByCode(String code);
    CouponDto saveOneByCode(String code, User user);
}
