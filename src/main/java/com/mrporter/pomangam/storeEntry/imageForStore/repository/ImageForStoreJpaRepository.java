package com.mrporter.pomangam.storeEntry.imageForStore.repository;

import com.mrporter.pomangam.storeEntry.imageForStore.domain.ImageForStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ImageForStoreJpaRepository extends JpaRepository<ImageForStore, Integer> {
}

