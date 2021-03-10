package com.mrporter.pomangam.client.repositories.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.QDeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.QDeliveryDetailSite;
import com.mrporter.pomangam.client.domains.store.QStoreMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface DeliverySiteJpaRepository extends JpaRepository<DeliverySite, Long>, DeliverySiteCustomRepository {
    /**
     * 유효한 배달지 전체 검색
     *
     * @param pageable
     * @return 배달지 전체 List 반환 (Page Wrapping)
     */
    Page<DeliverySite> findAllByIsActiveIsTrue(Pageable pageable);

    /**
     * 유효한 배달지 인덱스 검색
     *
     * @param idx 배달지 인덱스
     * @return 검색결과(배달지) 반환
     */
    DeliverySite findByIdxAndIsActiveIsTrue(Long idx);

    /**
     * 유효한 배달지 개수 검색
     *
     * @return 배달지 총 개수 반환
     */
    long countByIsActiveIsTrue();
}

interface DeliverySiteCustomRepository {
    /**
     * 배달지 전체 검색
     * FetchJoin 사용하여 N+1 문제 해결
     *
     * @param pageable
     * @return 배달지 전체 List 반환 (Page Wrapping)
     */
    Page<DeliverySite> findAllFetchJoinByIsActiveIsTrue(Pageable pageable);

    /**
     * 배달지 검색
     * 배달지명, 배달지주소, 배달지캠퍼스(대소문자 무시) 검색
     *
     * @param query 검색 문자열
     * @return 검색결과(리스트) 반환, 검색 실패 시 빈 리스트 반환
     */
    List<DeliverySite> search(String query);

    /**
     * 업체에 해당되는 배달지 검색
     *
     * @param sIdx 업체 인덱스
     * @return 검색결과(리스트) 반환, 검색 실패 시 빈 리스트 반환
     */
    List<DeliverySite> findAllByIdxStore(Long sIdx);
}

/*
    QueryDSL 구현부
*/
@Transactional(readOnly = true)
class DeliverySiteCustomRepositoryImpl extends QuerydslRepositorySupport implements DeliverySiteCustomRepository {

    public DeliverySiteCustomRepositoryImpl() {
        super(DeliverySite.class);
    }

    @Override
    public Page<DeliverySite> findAllFetchJoinByIsActiveIsTrue(Pageable pageable) {
        QDeliverySite deliverySite = QDeliverySite.deliverySite;
        QDeliveryDetailSite detailSite = QDeliveryDetailSite.deliveryDetailSite;
        List<DeliverySite> results =
                from(deliverySite)
                .select(deliverySite)
                .leftJoin(deliverySite.detailSites, detailSite)
                .fetchJoin()
                .where(deliverySite.isActive.isTrue())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }

    public List<DeliverySite> search(String query) {
        QDeliverySite deliverySite = QDeliverySite.deliverySite;
        return from(deliverySite)
                .select(deliverySite)
                .where(deliverySite.isActive.isTrue()
                .and(deliverySite.name.containsIgnoreCase(query)
                .or(deliverySite.location.containsIgnoreCase(query)
                .or(deliverySite.campus.containsIgnoreCase(query)))))
                .fetch();
    }

    @Override
    public List<DeliverySite> findAllByIdxStore(Long sIdx) {
        QDeliverySite deliverySite = QDeliverySite.deliverySite;
        QStoreMapper storeMapper = QStoreMapper.storeMapper;
        return from(deliverySite)
                .select(deliverySite)
                .join(storeMapper).on(storeMapper.deliverySite.idx.eq(deliverySite.idx))
                .where(deliverySite.isActive.isTrue()
                        .and(storeMapper.store.idx.eq(sIdx))
                        .and(storeMapper.store.isActive.isTrue()))
                .fetch();
    }
}
