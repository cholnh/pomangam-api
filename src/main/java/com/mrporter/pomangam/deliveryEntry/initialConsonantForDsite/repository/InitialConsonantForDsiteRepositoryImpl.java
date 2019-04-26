package com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class InitialConsonantForDsiteRepositoryImpl implements InitialConsonantForDsiteRepository {
    @PersistenceContext
    EntityManager em;


}
