package com.mrporter.pomangam.client.repositories.deliverysite.region;

import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RegionJpaRepository extends JpaRepository<Region, Integer> {

}
