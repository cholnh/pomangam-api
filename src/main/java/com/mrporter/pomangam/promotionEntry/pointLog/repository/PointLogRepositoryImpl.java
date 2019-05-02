package com.mrporter.pomangam.promotionEntry.pointLog.repository;

import com.mrporter.pomangam.common.util.queryRunner.QueryRunnerImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLogDto;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class PointLogRepositoryImpl implements PointLogRepository {
    @PersistenceContext
    EntityManager em;

    QueryRunnerImpl queryRunner;

    @Override
    public PointLogDto getLastNode(Integer customerIdx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT * FROM log_for_point_tbl WHERE customer_idx = ? ORDER BY sequence DESC LIMIT 1")
                .setParameter(1, customerIdx);

        List<PointLogDto> dtoList = new JpaResultMapper().list(nativeQuery, PointLogDto.class);
        if(dtoList.isEmpty()) {
            return null;
        } else {
            return dtoList.get(0);
        }
    }

    @Override
    public List<PointLogDto> findByCustomerIdx(Integer customerIdx, PageRequest pageRequest) {
        if(customerIdx == null) {
            return null;
        }

        int page = pageRequest == null ? 0 : pageRequest.getPage();
        int size = pageRequest == null ? 10 : pageRequest.getSize();

        List<PointLogDto> pointLogDtoList = em
                .createNativeQuery("SELECT * FROM log_for_point_tbl WHERE customer_idx = ? ORDER BY sequence DESC")
                .setParameter(1, customerIdx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( PointLogDto.class ) )
                .setFirstResult(page*size)
                .setMaxResults(size)
                .getResultList();

        return pointLogDtoList;
    }

}