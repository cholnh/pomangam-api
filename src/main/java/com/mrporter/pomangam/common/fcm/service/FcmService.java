package com.mrporter.pomangam.common.fcm.service;

import com.mrporter.pomangam.common.fcm.domain.FcmToken;

import java.util.Map;

public interface FcmService {
    FcmToken post(FcmToken token);
    FcmToken patch(FcmToken token);

    String sendToAll(Map<String, Object> paramInfo);
    String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Integer deliverySiteIdx);
}
