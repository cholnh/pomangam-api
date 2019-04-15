package com.mrporter.pomangam.deliveryEntry.deliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
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
    public List<DeliverySiteDto> findByQuery(String query) {
        String sql = "(SELECT ds.*, cnt.count FROM delivery_site_tbl ds LEFT OUTER JOIN count_search_delivery_site_tbl cnt ON ds.idx = cnt.delivery_site_idx where ds.name like :name ORDER BY cnt.count DESC) " +
                     " UNION " +
                     "(SELECT ds.*, cnt.count FROM delivery_site_tbl ds LEFT OUTER JOIN count_search_delivery_site_tbl cnt ON ds.idx = cnt.delivery_site_idx where ds.name not like :name AND ds.location like :name ORDER BY cnt.count DESC)";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter("name", "%"+query+"%");

        List<DeliverySiteDto> dList = new JpaResultMapper().list(nativeQuery, DeliverySiteDto.class);

        return dList;
    }

    @Override
    public List<DeliverySiteDto> findByRegionCategoryIdx(Integer regionCategoryIdx) {
        String sql = "SELECT * FROM delivery_site_tbl ds where ds.region_category_idx = ?";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, regionCategoryIdx);

        List<DeliverySiteDto> dList = new JpaResultMapper().list(nativeQuery, DeliverySiteDto.class);

        return dList;
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
