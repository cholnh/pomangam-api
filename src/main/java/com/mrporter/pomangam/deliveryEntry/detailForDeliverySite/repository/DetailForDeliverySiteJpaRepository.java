package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface DetailForDeliverySiteJpaRepository extends JpaRepository<DetailForDeliverySite, Integer> {
    DetailForDeliverySite getDetailForDeliverySiteByIdx(@Param("idx") Integer idx);
}
