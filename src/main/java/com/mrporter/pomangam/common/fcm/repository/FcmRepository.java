package com.mrporter.pomangam.common.fcm.repository;

import com.mrporter.pomangam.common.fcm.domain.FcmToken;

import java.util.List;

public interface FcmRepository {
    List<FcmToken> getTokens();
    List<FcmToken> getTokensByDeliverySiteIdx(Integer deliverySiteIdx);
}
