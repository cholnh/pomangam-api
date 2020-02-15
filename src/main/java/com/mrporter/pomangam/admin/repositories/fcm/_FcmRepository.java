package com.mrporter.pomangam.admin.repositories.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;

import java.util.List;

public interface _FcmRepository {
    List<FcmToken> getTokens();
    List<FcmToken> getTokensByDeliverySiteIdx(Long deliverySiteIdx);
}
