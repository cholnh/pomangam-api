package com.mrporter.pomangam.test.data.storeOwner;

import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerToken;
import com.mrporter.pomangam.client.repositories.fcm.owner.FcmOwnerTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoreOwnerTokenData {

    @Autowired
    FcmOwnerTokenJpaRepository fcmOwnerTokenJpaRepository;

    @Transactional
    public void of(Long idx, String idOwner, String token) {
        FcmOwnerToken fcmToken = FcmOwnerToken.builder()
                .idx(idx)
                .token(token)
                .idOwner(idOwner)
                .build();
        fcmOwnerTokenJpaRepository.save(fcmToken);
    }
}