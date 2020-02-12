package com.mrporter.pomangam.client.repositories.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface DetailJpaRepository extends JpaRepository<DeliveryDetailSite, Integer> {

}
