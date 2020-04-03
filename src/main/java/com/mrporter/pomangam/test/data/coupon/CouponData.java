package com.mrporter.pomangam.test.data.coupon;

import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class CouponData {

    @Autowired
    CouponJpaRepository couponJpaRepository;

    @Transactional
    public void of(Long idx, Long uIdx, String title, String code, LocalDateTime begin, LocalDateTime end, int discountCost, boolean isUsed) {
        Coupon coupon = Coupon.builder()
                .idx(idx)
                .isUsed(isUsed)
                .title(title)
                .code(code.replaceAll("-", ""))
                .beginDate(begin)
                .endDate(end)
                .discountCost(discountCost)
                .user(uIdx == null ? null : User.builder().idx(uIdx).build())
                .build();
        couponJpaRepository.save(coupon);
    }
}