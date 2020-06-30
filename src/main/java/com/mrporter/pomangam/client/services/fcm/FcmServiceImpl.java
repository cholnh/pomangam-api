package com.mrporter.pomangam.client.services.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import com.mrporter.pomangam.client.repositories.fcm.FcmRepositoryImpl;
import com.mrporter.pomangam.client.repositories.fcm.FcmTokenJpaRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class FcmServiceImpl implements FcmService {

    FcmRepositoryImpl fcmRepo;
    FcmTokenJpaRepository fcmTokenRepo;
    AndroidPushNotificationsServiceImpl androidPushNotificationsService;

    @Override
    public FcmToken post(FcmToken token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        token.setRegisterDate(LocalDateTime.now());
        return fcmTokenRepo.save(token);
    }

    @Override
    public FcmToken patch(FcmToken token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        final FcmToken fetched = fcmTokenRepo.findByIdxAndIsActiveIsTrue(token.getIdx());

        if (token.getToken() != null) {
            fetched.setToken(token.getToken());
        }
        if (token.getUser() != null) {
            fetched.setUser(token.getUser());
        }
        if (token.getIsActive() != null) {
            fetched.setIsActive(token.getIsActive());
        }
        return fcmTokenRepo.save(fetched);
    }

    @Override
    public String send(Map<String, Object> paramInfo) {
        List<FcmToken> tokens = new ArrayList<>();
        tokens.add(new FcmToken(null, paramInfo.get("fcmToken")+"", null));
        return send(paramInfo, tokens);
    }

    @Override
    public String sendToAll(Map<String, Object> paramInfo) {
        List<FcmToken> tokens = fcmRepo.getTokens();
        return send(paramInfo, tokens);
    }

    @Override
    public String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Long dIdx) {
        List<FcmToken> tokens = fcmRepo.getTokensByDeliverySiteIdx(dIdx);
        return send(paramInfo, tokens);
    }

    @Override
    public String send(String title, String message, FcmToken...tokens) {
        if(tokens == null || tokens.length == 0) return null;

        Map<String, Object> paramInfo = new HashMap<>();
        paramInfo.put("title", title);
        paramInfo.put("message", message);

        return send(paramInfo, Arrays.asList(tokens));
    }

    private String send(Map<String, Object> paramInfo, List<FcmToken> tokens) {
        if(tokens == null) return null;

        String firebaseResponse = null;
        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();

        // get token from db
        for(FcmToken token : tokens) {
            array.put(token.getToken());
        }

        // set notification
        JSONObject notification = new JSONObject();
        notification.put("title", paramInfo.get("title")+"");
        notification.put("body", paramInfo.get("message")+"");
        notification.put("sound", "default");

        if(array.length() == 1) {
            body.put("to", array.get(0));
        } else {
            body.put("registration_ids", array);
        }
        body.put("notification", notification);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            firebaseResponse = pushNotification.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return firebaseResponse;
    }
}