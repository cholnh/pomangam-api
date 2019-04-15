package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.domain.CountSearchDeliverySite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CountSearchDeliverySiteJpaRepository extends JpaRepository<CountSearchDeliverySite, Integer> {
}
