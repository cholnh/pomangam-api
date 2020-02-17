package com.mrporter.pomangam.client.repositories.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface DeliveryDetailJpaRepository extends JpaRepository<DeliveryDetailSite, Long> {
    List<DeliveryDetailSite> findByDeliverySite_Idx(@Param("idx") Long dIdx);
}
