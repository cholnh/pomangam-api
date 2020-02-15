package com.mrporter.pomangam.client.repositories.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class FcmRepositoryImpl implements FcmRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<FcmToken> getTokens() {
        Query nativeQuery = em
                .createNativeQuery("SELECT * FROM fcm_token_tbl WHERE state = 1");
        List<FcmToken> tokens = new JpaResultMapper().list(nativeQuery, FcmToken.class);
        return tokens;
    }

    @Override
    public List<FcmToken> getTokensByDeliverySiteIdx(Long deliverySiteIdx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT * FROM fcm_token_tbl WHERE delivery_site_idx = :deliverySiteIdx AND state = 1")
                .setParameter("deliverySiteIdx", deliverySiteIdx);
        List<FcmToken> tokens = new JpaResultMapper().list(nativeQuery, FcmToken.class);
        return tokens;
    }
}
