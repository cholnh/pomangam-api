package com.mrporter.pomangam.advertiseEntry.imageForMain.repository;

import com.mrporter.pomangam.advertiseEntry.imageForMain.domain.ImageForMainDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class ImageForMainRepositoryImpl implements ImageForMainRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<ImageForMainDto> getImagesByDeliverySiteIdx(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM imgpath_for_main_tbl " +
                "WHERE idx IN " +
                    "(SELECT imgpath_main_idx " +
                    "FROM dsite_link_imgpath_main_tbl " +
                    "WHERE delivery_site_idx = ?)" +
                "ORDER BY sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<ImageForMainDto> imgList = new JpaResultMapper().list(nativeQuery, ImageForMainDto.class);

        return imgList;
    }
}
