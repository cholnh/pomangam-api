package com.mrporter.pomangam.client.repositories.store;

import com.mrporter.pomangam.client.domains.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface StoreJpaRepository extends JpaRepository<Store, Integer> {
    List<Store> findByDeliverySite_Idx(Integer idx);
}
