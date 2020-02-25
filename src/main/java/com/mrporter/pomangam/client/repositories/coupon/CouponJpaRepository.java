package com.mrporter.pomangam.client.repositories.coupon;

import com.mrporter.pomangam.client.domains.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface CouponJpaRepository extends JpaRepository<Coupon, Long>, CouponCustomRepository {
    List<Coupon> findByUser_IdxAndIsActiveIsTrueAndIsUsedIsFalse(Long idxUser);
}

interface CouponCustomRepository {

}

@Transactional(readOnly = true)
class CouponCustomRepositoryImpl extends QuerydslRepositorySupport implements CouponCustomRepository {

    public CouponCustomRepositoryImpl() {
        super(Coupon.class);
    }


}
