package com.mrporter.pomangam.common.fcm.service;

import com.mrporter.pomangam.common.fcm.domain.FcmToken;
import com.mrporter.pomangam.common.fcm.repository.FcmJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FcmServiceImpl implements FcmService {

    FcmJpaRepository fcmJpaRepository;

    @Override
    public void addToken(FcmToken token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        fcmJpaRepository.save(token);
    }
}
