package com.mrporter.pomangam.client.services.user.coupon;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import com.mrporter.pomangam.client.domains.user.coupon.CouponDto;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponJpaRepository;
import com.mrporter.pomangam.client.services.user.coupon.exception.CouponException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {

    CouponJpaRepository couponRepo;

    @Override
    public List<CouponDto> findAllByIdxUser(Long uIdx) {
        return CouponDto.fromEntities(couponRepo.findByUser_IdxAndIsActiveIsTrueOrderByIsUsedAscIdxDesc(uIdx));
    }

    @Override
    public CouponDto findOneByIdx(Long cIdx) {
        return CouponDto.fromEntity(couponRepo.findByIdxAndIsActiveIsTrue(cIdx));
    }

    @Override
    public CouponDto findOneByCode(String code) {
        return CouponDto.fromEntity(couponRepo.findByCodeAndIsActiveIsTrueAndUserIsNull(code)
                .orElseThrow(() -> new CouponException("invalid coupon code")));
    }

    @Override
    public CouponDto saveOneByCode(String code, User user) {
        Coupon coupon = couponRepo.findByCodeAndIsActiveIsTrueAndUserIsNull(code)
                .orElseThrow(() -> new CouponException("invalid coupon code"));
        coupon.setUser(user);
        return CouponDto.fromEntity(couponRepo.save(coupon));
    }
}

