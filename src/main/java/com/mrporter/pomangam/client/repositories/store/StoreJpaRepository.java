package com.mrporter.pomangam.client.repositories.store;

import com.mrporter.pomangam.client.domains.ordertime.QOrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTimeMapper;
import com.mrporter.pomangam.client.domains.store.QStore;
import com.mrporter.pomangam.client.domains.store.SortType;
import com.mrporter.pomangam.client.domains.store.Store;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.Optional;


@RepositoryRestResource(exported = false)
public interface StoreJpaRepository extends JpaRepository<Store, Long>, StoreCustomRepository {
    Page<Store> findByIdxDeliverySiteAndIsActiveIsTrueOrderBySequenceAsc(Long dIdx, Pageable pageable);
    Store findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface StoreCustomRepository {

    /**
     * 해당 시간(orderTime), 해당 배달지(deliverySite) 에 주문이 가능한 업체를 리스트 형태로 반환
     * orderTime's isActive, store's isActive, mapper's isActive 확인
     *
     * @param oIdx 주문시간 인덱스
     * @param dIdx 배달지 인덱스
     * @param pageable pageable
     * @return  주문 가능한 업체 리스트
     */
    Page<Store> findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(Long oIdx, Long dIdx, Pageable pageable, SortType sortType);

    long countByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(Long oIdx, Long dIdx);
}

@Transactional(readOnly = true)
class StoreCustomRepositoryImpl extends QuerydslRepositorySupport implements StoreCustomRepository {

    public StoreCustomRepositoryImpl() {
        super(Store.class);
    }

    @Override
    public Page<Store> findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(Long oIdx, Long dIdx, Pageable pageable, SortType sortType) {
        final QOrderTimeMapper mapper = QOrderTimeMapper.orderTimeMapper;
        final QOrderTime orderTime = QOrderTime.orderTime;

        JPQLQuery<Store> query =
                from(orderTime)
                .select(mapper.store)
                .join(mapper).on(orderTime.idx.eq(mapper.orderTime.idx))
                .where(mapper.orderTime.idx.eq(oIdx)
                .and(mapper.isActive.isTrue())
                .and(mapper.store.isActive.isTrue())
                .and(orderTime.isActive.isTrue())
                .and(mapper.store.idxDeliverySite.eq(dIdx)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        switch (sortType) {
            case SORT_BY_RECOMMEND_DESC:
                // query.orderBy(mapper.store.idx.desc());
                break;
            case SORT_BY_ORDER_DESC:
                query.orderBy(mapper.store.cntOrder.desc());
                break;
            case SORT_BY_STAR_DESC:
                query.orderBy(mapper.store.avgStar.desc());
                break;
            case SORT_BY_REVIEW_DESC:
                query.orderBy(mapper.store.cntReview.desc());
                break;
        }
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    @Override
    public long countByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(Long oIdx, Long dIdx) {
        final QOrderTimeMapper mapper = QOrderTimeMapper.orderTimeMapper;
        final QOrderTime orderTime = QOrderTime.orderTime;

        return from(orderTime)
            .select(mapper.store.count())
            .join(mapper).on(orderTime.idx.eq(mapper.orderTime.idx))
            .where(mapper.orderTime.idx.eq(oIdx)
            .and(mapper.isActive.isTrue())
            .and(mapper.store.isActive.isTrue())
            .and(orderTime.isActive.isTrue())
            .and(mapper.store.idxDeliverySite.eq(dIdx)))
            .fetchFirst();
    }
}