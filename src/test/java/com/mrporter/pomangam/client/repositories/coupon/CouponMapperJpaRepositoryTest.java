package com.mrporter.pomangam.client.repositories.coupon;

import com.mrporter.pomangam.client.domains.coupon.Coupon;
import com.mrporter.pomangam.client.domains.coupon.CouponMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner.class)
public class CouponMapperJpaRepositoryTest {

    @Autowired
    private CouponMapperJpaRepository couponMapperJpaRepository;

    @Test
    public void CouponMapper_N1_문제_테스트() {
        List<CouponMapper> couponMappers = couponMapperJpaRepository.findByOrder_Idx(1L);
        for(CouponMapper couponMapper : couponMappers) {
            Coupon mapperCoupon = couponMapper.getCoupon();
            mapperCoupon.setIsUsed(true);
        }
    }
}
