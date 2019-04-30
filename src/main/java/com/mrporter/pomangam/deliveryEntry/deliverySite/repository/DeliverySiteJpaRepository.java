package com.mrporter.pomangam.deliveryEntry.deliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface DeliverySiteJpaRepository extends JpaRepository<DeliverySite, Integer> {
    //List<KakaoAuth> findByRegionCategoryIdx(@Param("regionCategoryIdx") Integer regionCategoryIdx);
}
