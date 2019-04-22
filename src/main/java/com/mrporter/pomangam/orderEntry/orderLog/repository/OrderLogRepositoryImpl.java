package com.mrporter.pomangam.orderEntry.orderLog.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderLogRepositoryImpl implements OrderLogRepository {

    @PersistenceContext
    EntityManager em;

}
