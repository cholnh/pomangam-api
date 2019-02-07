package com.mrporter.pomangam.store.repository;

import com.mrporter.pomangam.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface StoreRepository extends JpaRepository<Store, Integer> {
}

