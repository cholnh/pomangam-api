package com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.domain.cmtAdvertiseForMainWithCommentAllDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class CmtAdvertiseForMainRepositoryImpl implements cmtAdvertiseForMainRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<cmtAdvertiseForMainWithCommentAllDto> getCmtAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx) {
        String sql = "SELECT ca.idx AS comment_all_idx," +
                            "ca.store_idx AS store_idx," +
                            "ca.title AS title," +
                            "CONCAT(LEFT(ca.contents, 15), '...') AS contents," +
                            "ca.state_active AS c_state_active," +
                            "ad.imgpath AS imgpath," +
                            "ad.state_active AS a_state_active," +
                            "ad.sequence AS sequence " +
                    "FROM comment_for_all_tbl ca, cmt_advertise_for_main_tbl ad " +
                    "WHERE ca.idx = ad.comment_all_idx " +
                    "AND ad.idx IN " +
                        "(SELECT cmt_advertise_main_idx " +
                        "FROM dsite_link_cmt_advertise_main_tbl " +
                        "WHERE delivery_site_idx = ?) " +
                    "ORDER BY ad.sequence";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        List<cmtAdvertiseForMainWithCommentAllDto> cmtList = new JpaResultMapper().list(nativeQuery, cmtAdvertiseForMainWithCommentAllDto.class);

        return cmtList;
    }
}
