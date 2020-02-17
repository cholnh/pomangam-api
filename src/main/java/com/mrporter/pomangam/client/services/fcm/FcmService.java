package com.mrporter.pomangam.client.services.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;

import java.util.Map;

public interface FcmService {
    FcmToken post(FcmToken token);
    FcmToken patch(FcmToken token);

    String sendToAll(Map<String, Object> paramInfo);
    String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Long deliverySiteIdx);
}
