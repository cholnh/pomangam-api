package com.mrporter.pomangam.test.mapObserver.repository;

import com.mrporter.pomangam.test.mapObserver.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface MapObserverJpaRepository extends JpaRepository<Map, Integer> {
}

