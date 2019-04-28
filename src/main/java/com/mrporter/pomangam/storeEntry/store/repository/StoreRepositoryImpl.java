package com.mrporter.pomangam.storeEntry.store.repository;

import com.mrporter.pomangam.common.util.sqlInjection.SqlInjection;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.storeEntry.store.domain.StoreDto;
import com.mrporter.pomangam.storeEntry.store.domain.StoreSummaryDto;
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
public class StoreRepositoryImpl implements StoreRepository {
    @PersistenceContext
    EntityManager em;

    SqlInjection sqlInjection;

    @Override
    public List<StoreDto> findByQuery(String query, Integer delivery_site_idx) {
        Query nativeQuery = em
                .createNativeQuery(
                "SELECT  " +
                        "    s.* " +
                        "FROM " +
                        "    store_tbl s, " +
                        "    schedule_for_store_tbl sc " +
                        "WHERE " +
                        "   s.idx IN (SELECT store_idx FROM ordertime_for_store_tbl WHERE delivery_site_idx = :didx group by store_idx) " +
                        "        AND s.idx = sc.store_idx " +
                        "        AND sc.state_active = 1 " +
                        "        AND sc.state_pause = 0 " +
                        "        AND s.name LIKE :name "
                )
                .setParameter("didx", delivery_site_idx)
                .setParameter("name", "%"+query+"%");
        List<StoreDto> storeDtoList = new JpaResultMapper().list(nativeQuery, StoreDto.class);

        return storeDtoList;
    }

    @Override
    public List<StoreSummaryDto> findByType(Integer delivery_site_idx, Integer type, String orderBy, PageRequest pageRequest) {
        List storeDtoList = em
                .createNativeQuery(
                        "SELECT  " +
                                "    s.idx, s.name, s.description, s.cnt_like, s.cnt_comment, s.sequence, s.type, img.imgpath " +
                                "FROM " +
                                "    schedule_for_store_tbl sc, " +
                                "    store_tbl s " +
                                "LEFT OUTER JOIN imgpath_for_store_tbl img " +
                                "ON img.store_idx = s.idx AND img.type = 0 " +
                                "WHERE " +
                                "   s.idx IN (SELECT store_idx FROM ordertime_for_store_tbl WHERE delivery_site_idx = :didx group by store_idx) " +
                                "        AND s.idx = sc.store_idx " +
                                "        AND sc.state_active = 1 " +
                                "        AND sc.state_pause = 0 " +
                                "        AND s.type = :type " +
                                (orderBy != null && !sqlInjection.isSQLInjection(orderBy) ? "ORDER BY " + orderBy :"ORDER BY s.sequence DESC ")
                )
                .setParameter("didx", delivery_site_idx)
                .setParameter("type", type)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( StoreSummaryDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();

        return storeDtoList;
    }
}
