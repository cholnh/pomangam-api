package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@AllArgsConstructor
public class CountSearchDeliverySiteRepositoryImpl implements CountSearchDeliverySiteRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void addCount(Integer deliverySiteIdx) {
        String sql = "UPDATE count_search_delivery_site_tbl SET count=count+1 WHERE delivery_site_idx = :didx";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter("didx", deliverySiteIdx);
        nativeQuery.executeUpdate();
    }

}
