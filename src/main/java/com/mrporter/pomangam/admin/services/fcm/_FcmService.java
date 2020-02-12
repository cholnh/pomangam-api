package com.mrporter.pomangam.admin.services.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;

import java.util.Map;

public interface _FcmService {
    FcmToken post(FcmToken token);
    FcmToken patch(FcmToken token);

    String sendToAll(Map<String, Object> paramInfo);
    String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Integer deliverySiteIdx);
}
