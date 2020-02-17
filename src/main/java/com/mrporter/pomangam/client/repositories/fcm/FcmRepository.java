package com.mrporter.pomangam.client.repositories.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;

import java.util.List;

public interface FcmRepository {
    List<FcmToken> getTokens();
    List<FcmToken> getTokensByDeliverySiteIdx(Long deliverySiteIdx);
}
