package com.mrporter.pomangam.storeEntry.store.repository;

import com.mrporter.pomangam.storeEntry.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface StoreJpaRepository extends JpaRepository<Store, Integer>, StoreRepository {
}

