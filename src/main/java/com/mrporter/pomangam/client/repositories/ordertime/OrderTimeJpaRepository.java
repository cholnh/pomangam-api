package com.mrporter.pomangam.client.repositories.ordertime;

import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTimeMapper;
import com.mrporter.pomangam.client.domains.store.QStore;
import com.mrporter.pomangam.client.domains.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface OrderTimeJpaRepository extends JpaRepository<OrderTime, Long>, OrderTimeCustomRepository {

}

interface OrderTimeCustomRepository {

    /**
     * 해당 시간(orderTime), 해당 배달지(deliverySite) 에 주문이 가능한 업체를 리스트 형태로 반환
     * orderTime's isActive, store's isActive, mapper's isActive 확인
     *
     * @param oIdx 주문시간 인덱스
     * @param dIdx 배달지 인덱스
     * @param pageable pageable
     * @return  주문 가능한 업체 리스트
     */
    Page<Store> findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(Long oIdx, Long dIdx, Pageable pageable);
}

@Transactional(readOnly = true)
class OrderTimeCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderTimeCustomRepository {

    public OrderTimeCustomRepositoryImpl() {
        super(OrderTime.class);
    }

    @Override
    public Page<Store> findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(Long oIdx, Long dIdx, Pageable pageable) {
        final QOrderTimeMapper mapper = QOrderTimeMapper.orderTimeMapper;
        final QOrderTime orderTime = QOrderTime.orderTime;

        List<Store> results =
            from(orderTime)
            .select(mapper.store)
            .join(mapper).on(orderTime.idx.eq(mapper.orderTime.idx))
            .where(mapper.orderTime.idx.eq(oIdx)
                .and(mapper.isActive.isTrue())
                .and(mapper.store.isActive.isTrue())
                .and(orderTime.isActive.isTrue())
                .and(mapper.store.idxDeliverySite.eq(dIdx)))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }
}