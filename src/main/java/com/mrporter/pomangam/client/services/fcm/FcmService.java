package com.mrporter.pomangam.client.services.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;

import java.util.Map;

public interface FcmService {
    FcmToken post(FcmToken token);
    FcmToken patch(FcmToken token);

    String send(Map<String, Object> paramInfo);
    String send(String title, String message, FcmToken...tokens);
    String sendToAll(Map<String, Object> paramInfo);
    String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Long deliverySiteIdx);
}
