package com.mrporter.pomangam.test.mapObserver.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class MapObserverRepositoryImpl implements MapObserverRepository {
    @PersistenceContext
    EntityManager em;


}
