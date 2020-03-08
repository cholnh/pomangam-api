package com.mrporter.pomangam.test.data.fcmtoken;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import com.mrporter.pomangam.client.repositories.fcm.FcmTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FcmTokenData {

    @Autowired
    FcmTokenJpaRepository fcmTokenJpaRepository;

    @Transactional
    public void of(Long idx, String title) {
        FcmToken fcmToken = FcmToken.builder()
                .idx(idx)
                .token(title)
                .build();
        fcmTokenJpaRepository.save(fcmToken);
    }
}