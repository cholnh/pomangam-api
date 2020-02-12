package com.mrporter.pomangam._bases.securities.guest.repository;

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