package com.mrporter.pomangam.deliveryEntry.deliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class DeliverySiteRepositoryImpl implements DeliverySiteRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public ResponseEntity<?> findByQuery(String query) {
        String sql = "SELECT * FROM delivery_site_tbl ds where ds.name like ?1 OR ds.location like ?2 ";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, "%"+query+"%");
        nativeQuery.setParameter(2, "%"+query+"%");

        List<DeliverySiteDto> dList = new JpaResultMapper().list(nativeQuery, DeliverySiteDto.class);

        return ResponseEntity.ok(dList);
    }

    @Override
    public ResponseEntity<?> findByRegionCategoryIdx(Integer regionCategoryIdx) {
        String sql = "SELECT * FROM delivery_site_tbl ds where ds.region_category_idx = ?";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, regionCategoryIdx);

        List<DeliverySiteDto> dList = new JpaResultMapper().list(nativeQuery, DeliverySiteDto.class);

        return ResponseEntity.ok(dList);
    }

   @Override
   public DeliverySiteDto getByDeliverySiteIdx(Integer deliverySiteIdx){
       String sql = "SELECT * FROM delivery_site_tbl ds where ds.idx = ?";
       Query nativeQuery = em.createNativeQuery(sql);
       nativeQuery.setParameter(1, deliverySiteIdx);

       DeliverySiteDto dto = new JpaResultMapper().uniqueResult(nativeQuery, DeliverySiteDto.class);

       return dto;
   }

}
