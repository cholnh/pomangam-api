package com.mrporter.pomangam.client.services.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import com.mrporter.pomangam.client.repositories.fcm.FcmJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.FcmRepositoryImpl;
import com.mrporter.pomangam._bases.utils.time.CustomTime;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class FcmServiceImpl implements FcmService {

    FcmRepositoryImpl fcmRepository;
    FcmJpaRepository fcmJpaRepository;
    AndroidPushNotificationsServiceImpl androidPushNotificationsService;

    @Override
    public FcmToken post(FcmToken token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        token.setRegisterDate(LocalDateTime.now());
        return fcmJpaRepository.save(token);
    }

    @Override
    public FcmToken patch(FcmToken token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        Optional<FcmToken> optional = fcmJpaRepository.findById(token.getIdx());
        if(optional.isPresent()) {
            final FcmToken fetched = optional.get();

            if (token.getToken() != null) {
                fetched.setToken(token.getToken());
            }
            if (token.getUser() != null) {
                fetched.setUser(token.getUser());
            }
            if (token.getIsActive() != null) {
                fetched.setIsActive(token.getIsActive());
            }
            return fcmJpaRepository.save(fetched);
        } else {
            return null;
        }
    }

    @Override
    public String sendToAll(Map<String, Object> paramInfo) {
        List<FcmToken> tokens = fcmRepository.getTokens();
        return send(paramInfo, tokens);
    }

    @Override
    public String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Long deliverySiteIdx) {
        List<FcmToken> tokens = fcmRepository.getTokensByDeliverySiteIdx(deliverySiteIdx);
        return send(paramInfo, tokens);
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


        try {
            notification.put("title", URLEncoder.encode(paramInfo.get("title")+"" ,"UTF-8"));
            notification.put("body",  URLEncoder.encode(paramInfo.get("message")+"", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        body.put("registration_ids", array);
        body.put("data", notification);

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
        //System.out.println("firebaseResponse : " + firebaseResponse);
        return firebaseResponse;
    }
}
