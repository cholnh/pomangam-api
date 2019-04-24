package com.mrporter.pomangam.promotionEntry.coupon.repository;

import com.mrporter.pomangam.common.util.queryRunner.QueryRunnerImpl;
import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    @PersistenceContext
    EntityManager em;

    QueryRunnerImpl queryRunner;

    @Override
    public int countCoupon(Integer customerIdx) throws Exception {
        Map<String, Object> map = queryRunner.getOne("SELECT count(*) as count FROM coupon_tbl cp where cp.state_active = 1 AND cp.customer_idx = ?", customerIdx);
        return Integer.parseInt(map.get("count")+"");
    }

    @Override
    public CouponDto findByIdx(Integer idx) {
        Query nativeQuery = em.createNativeQuery(
                "SELECT * FROM coupon_tbl cp where cp.idx = ?"
        );
        nativeQuery.setParameter(1, idx);

        List<CouponDto> dtoList = new JpaResultMapper().list(nativeQuery, CouponDto.class);
        if(dtoList == null || dtoList.isEmpty()) {
            return null;
        } else {
            return dtoList.get(0);
        }
    }

    @Override
    public List<CouponDto> findAll() {
        Query nativeQuery = em.createNativeQuery(
                "SELECT * FROM coupon_tbl"
        );

        List<CouponDto> dtoList = new JpaResultMapper().list(nativeQuery, CouponDto.class);
        return dtoList;
    }

    @Override
    public List<CouponDto> findValidByCustomerId(String customer_id) {
        Query nativeQuery = em.createNativeQuery(
                "SELECT cp.* FROM coupon_tbl cp, user_tbl u where u.id = ? AND cp.customer_idx = u.idx AND cp.state_active = 1 AND cp.begin_date <= NOW() AND (cp.end_date IS NULL OR cp.end_date > NOW())"
        );
        nativeQuery.setParameter(1, customer_id);

        List<CouponDto> dtoList = new JpaResultMapper().list(nativeQuery, CouponDto.class);
        return dtoList;
    }

    @Override
    public CouponDto getValidCouponByCode(String couponCode) {
        if(couponCode == null) {
            return null;
        }
        Query nativeQuery = em.createNativeQuery(
                "SELECT * FROM coupon_tbl cp where cp.code = ? AND state_active = 1 AND begin_date <= NOW() AND (end_date IS NULL OR end_date > NOW())"
        );
        nativeQuery.setParameter(1, couponCode);
        List<CouponDto> dtoList = new JpaResultMapper().list(nativeQuery, CouponDto.class);
        if(dtoList == null || dtoList.isEmpty()) {
            return null;
        } else {
            return dtoList.get(0);
        }
    }

    @Override
    public CouponDto getValidCouponByIdx(Integer couponIdx) {
        if(couponIdx == null) {
            return null;
        }
        Query nativeQuery = em.createNativeQuery(
                "SELECT * FROM coupon_tbl cp where cp.idx ? AND state_active = 1 AND begin_date <= NOW() AND (end_date IS NULL OR end_date > NOW())"
        );
        nativeQuery.setParameter(1, couponIdx);
        List<CouponDto> dtoList = new JpaResultMapper().list(nativeQuery, CouponDto.class);
        if(dtoList == null || dtoList.isEmpty()) {
            return null;
        } else {
            return dtoList.get(0);
        }
    }

    @Override
    public boolean isValid(String couponCode) throws Exception {
        List<Map<String, Object>> lom = queryRunner.query("SELECT * FROM coupon_tbl cp where cp.code = ? AND state_active = 1 AND begin_date <= NOW() AND (end_date IS NULL OR end_date > NOW())", couponCode);
        return !(lom == null || lom.isEmpty());
    }

    @Override
    public void useCoupon(Integer couponIdx, Integer customerIdx, Integer guestIdx, Integer orderIdx) throws Exception {
        queryRunner.update("UPDATE coupon_tbl cp SET cp.state_active = 0 WHERE cp.idx = ?", couponIdx);

        int sequence = 1;
        Map<String, Object> map = queryRunner.getOne("SELECT MAX(sequence)+1 as sequence FROM log_for_coupon_tbl WHERE coupon_idx = ?", couponIdx);
        if(map != null) {
            sequence = Integer.parseInt(map.get("sequence")+"");
        }
        queryRunner.update("INSERT INTO log_for_coupon_tbl (`coupon_idx`, `customer_idx`, `guest_idx`, `order_idx`, `register_date`, `type`, `sequence`) VALUES (?, ?, ?, now(), '2', ?)", couponIdx, customerIdx, guestIdx, orderIdx, sequence);
    }

    @Override
    public CouponDto save(CouponDto coupon) {
        return null;
    }

    @Override
    public CouponDto update(Integer idx, CouponDto user) {
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