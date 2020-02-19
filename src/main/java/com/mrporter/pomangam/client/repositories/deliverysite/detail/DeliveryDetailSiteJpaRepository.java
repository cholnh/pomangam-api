package com.mrporter.pomangam.client.repositories.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface DeliveryDetailSiteJpaRepository extends JpaRepository<DeliveryDetailSite, Long>, DeliveryDetailSiteCustomRepository {
    List<DeliveryDetailSite> findByDeliverySite_IdxAndIsActiveIsTrue(@Param("idx") Long dIdx);
    DeliveryDetailSite findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface DeliveryDetailSiteCustomRepository {

}

@Transactional(readOnly = true)
class DeliveryDetailSiteCustomRepositoryImpl extends QuerydslRepositorySupport implements DeliveryDetailSiteCustomRepository {

    public DeliveryDetailSiteCustomRepositoryImpl() {
        super(DeliveryDetailSite.class);
    }


}
