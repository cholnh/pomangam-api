package com.mrporter.pomangam.test.data.fcmtoken;

import com.mrporter.pomangam.client.domains.fcm.client.FcmClientToken;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FcmTokenData {

    @Autowired
    FcmClientTokenJpaRepository fcmTokenJpaRepository;

    @Transactional
    public void of(Long idx, String title) {
        FcmClientToken fcmToken = FcmClientToken.builder()
                .idx(idx)
                .token(title)
                .build();
        fcmTokenJpaRepository.save(fcmToken);
    }
}