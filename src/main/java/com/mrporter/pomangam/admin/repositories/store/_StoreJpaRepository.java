package com.mrporter.pomangam.admin.repositories.store;

import com.mrporter.pomangam.client.domains.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface _StoreJpaRepository extends JpaRepository<Store, Integer> {

}
