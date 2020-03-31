package com.mrporter.pomangam.client.repositories.user.coupon;

import com.mrporter.pomangam.client.domains.user.coupon.CouponMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface CouponMapperJpaRepository extends JpaRepository<CouponMapper, Long>, CouponMapperCustomRepository {
    List<CouponMapper> findByOrder_Idx(Long oIdx);
}

interface CouponMapperCustomRepository {

}

@Transactional(readOnly = true)
class CouponMapperCustomRepositoryImpl extends QuerydslRepositorySupport implements CouponMapperCustomRepository {

    public CouponMapperCustomRepositoryImpl() {
        super(CouponMapper.class);
    }


}
