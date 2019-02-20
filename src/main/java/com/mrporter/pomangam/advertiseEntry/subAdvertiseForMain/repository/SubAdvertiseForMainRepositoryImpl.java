package com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class SubAdvertiseForMainRepositoryImpl implements SubAdvertiseForMainRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<SubAdvertiseForMainDto> getSubAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM sub_advertise_for_main_tbl " +
                "WHERE idx IN " +
                    "(SELECT sub_advertise_main_idx " +
                    "FROM dstie_link_sub_advertise_main_tbl " +
                    "WHERE delivery_site_idx = ?)" +
                "ORDER BY sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<SubAdvertiseForMainDto> subAdList = new JpaResultMapper().list(nativeQuery, SubAdvertiseForMainDto.class);

        return subAdList;
    }
}
