package com.mrporter.pomangam.advertiseEntry.advertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMainDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class AdvertiseForMainRepositoryImpl implements AdvertiseForMainRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<AdvertiseForMainDto> getAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM advertise_for_main_tbl " +
                "WHERE idx IN " +
                    "(SELECT advertise_main_idx " +
                    "FROM dstie_link_advertise_main_tbl " +
                    "WHERE delivery_site_idx = ?)" +
                "ORDER BY sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<AdvertiseForMainDto> adList = new JpaResultMapper().list(nativeQuery, AdvertiseForMainDto.class);

        return adList;
    }
}
