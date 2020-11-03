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
    Page<DeliverySite> findAllByIsActiveIsTrue(Pageable pageable);
    DeliverySite findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
    //List<DeliverySite> findAllByIsActiveIsTrueAndNameContainingOrLocationContainingOrCampusContaining(String query1, String query2, String query3);
}

interface DeliverySiteCustomRepository {
    Page<DeliverySite> findAllFetchJoinByIsActiveIsTrue(Pageable pageable); // N+1 문제 해결
    List<DeliverySite> search(String query);
    List<DeliverySite> findAllByIdxStore(Long sIdx);
}

@Transactional(readOnly = true)
class DeliverySiteCustomRepositoryImpl extends QuerydslRepositorySupport implements DeliverySiteCustomRepository {

    public DeliverySiteCustomRepositoryImpl() {
        super(DeliverySite.class);
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
}
