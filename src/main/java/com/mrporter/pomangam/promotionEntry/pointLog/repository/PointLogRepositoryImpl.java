package com.mrporter.pomangam.promotionEntry.pointLog.repository;

import com.mrporter.pomangam.common.util.queryRunner.QueryRunnerImpl;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLogDto;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class PointLogRepositoryImpl implements PointLogRepository {
    @PersistenceContext
    EntityManager em;

    QueryRunnerImpl queryRunner;

    @Override
    public PointLogDto getLastNode(Integer customerIdx) {
        PointLogDto dto = (PointLogDto) em
                .createNativeQuery("SELECT * FROM log_for_point_tbl WHERE customer_idx = ? ORDER BY sequence DESC LIMIT 1")
                .setParameter(1, customerIdx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( PointLogDto.class ) )
                .getSingleResult();
          return dto;
    }

}