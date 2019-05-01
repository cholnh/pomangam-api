package com.mrporter.pomangam.promotionEntry.notice.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {
    @PersistenceContext
    EntityManager em;
}