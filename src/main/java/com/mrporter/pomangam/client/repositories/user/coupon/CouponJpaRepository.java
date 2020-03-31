package com.mrporter.pomangam.client.repositories.user.coupon;

import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(exported = false)
public interface CouponJpaRepository extends JpaRepository<Coupon, Long>, CouponCustomRepository {
    List<Coupon> findByUser_IdxAndIsActiveIsTrue(Long idxUser);
    List<Coupon> findByUser_IdxAndIsActiveIsTrueOrderByIsUsedAscIdxDesc(Long idxUser);
    Coupon findByIdxAndIsActiveIsTrue(Long cIdx);
    Optional<Coupon> findByCodeAndIsActiveIsTrueAndUserIsNull(String code);
}

interface CouponCustomRepository {

}

@Transactional(readOnly = true)
class CouponCustomRepositoryImpl extends QuerydslRepositorySupport implements CouponCustomRepository {

    public CouponCustomRepositoryImpl() {
        super(Coupon.class);
    }


}
