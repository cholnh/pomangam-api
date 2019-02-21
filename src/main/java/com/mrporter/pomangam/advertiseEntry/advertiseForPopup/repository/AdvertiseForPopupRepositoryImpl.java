package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.repository;

import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain.AdvertiseForPopupDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class AdvertiseForPopupRepositoryImpl implements AdvertiseForPopupRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<AdvertiseForPopupDto> getAdvertisePopupsByDeliverySiteIdx(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM advertise_for_popup_tbl " +
                "WHERE idx IN " +
                    "(SELECT advertise_popup_idx " +
                    "FROM dstie_link_advertise_popup_tbl " +
                    "WHERE delivery_site_idx = ?)" +
                "ORDER BY sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<AdvertiseForPopupDto> adList = new JpaResultMapper().list(nativeQuery, AdvertiseForPopupDto.class);

        return adList;
    }
}
