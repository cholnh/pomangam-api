package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class DetailForDeliverySiteRepositoryImpl implements DetailForDeliverySiteRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<DetailForDeliverySiteDto> getDetailSitesByDeliverySiteIdxOrderBySequence(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM detail_for_delivery_site_tbl " +
                "WHERE delivery_site_idx = ? " +
                "ORDER BY sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<DetailForDeliverySiteDto> dList = new JpaResultMapper().list(nativeQuery, DetailForDeliverySiteDto.class);

        return dList;
    }

    @Override
    public List<DetailForDeliverySiteDto> findByDeliverySiteIdxOrderBySequence(Integer delivery_site_idx) {
        String sql = "SELECT d.* " +
                "FROM detail_for_delivery_site_tbl d " +
                "WHERE delivery_site_idx = ? " +
                "ORDER BY sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<DetailForDeliverySiteDto> dList = new JpaResultMapper().list(nativeQuery, DetailForDeliverySiteDto.class);

        return dList;
    }

}
