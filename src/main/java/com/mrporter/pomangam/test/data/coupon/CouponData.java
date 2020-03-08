package com.mrporter.pomangam.test.data.coupon;

import com.mrporter.pomangam.client.domains.coupon.Coupon;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.coupon.CouponJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class CouponData {

    @Autowired
    CouponJpaRepository couponJpaRepository;

    @Transactional
    public void of(Long idx, Long uIdx, String title, String code, LocalDateTime begin, int discountCost) {
        Coupon coupon = Coupon.builder()
                .idx(idx)
                .isUsed(false)
                .title(title)
                .code(code)
                .beginDate(begin)
                .discountCost(discountCost)
                .user(User.builder().idx(uIdx).build())
                .build();
        couponJpaRepository.save(coupon);
    }
}