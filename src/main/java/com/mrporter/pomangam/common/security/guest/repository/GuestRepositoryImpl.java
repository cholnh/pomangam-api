package com.mrporter.pomangam.common.security.guest.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class GuestRepositoryImpl implements GuestRepository {
    @PersistenceContext
    EntityManager em;


}