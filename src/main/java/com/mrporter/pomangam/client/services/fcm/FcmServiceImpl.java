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
        token.setRegister_date(CustomTime.curTimestampSql());
        return fcmJpaRepository.save(token);
    }

    @Override
    public FcmToken patch(FcmToken token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        Optional<FcmToken> optional = fcmJpaRepository.findById(token.getToken());
        if(optional.isPresent()) {
            final FcmToken fetched = optional.get();

            if (token.getCustomer_id() != null) {
                fetched.setCustomer_id(token.getCustomer_id());
            }
            if (token.getDelivery_site_idx() != null) {
                fetched.setDelivery_site_idx(token.getDelivery_site_idx());
            }
            if (token.getGuest_idx() != null) {
                fetched.setGuest_idx(token.getGuest_idx());
            }
            if(token.getOwner_id() != null) {
                fetched.setOwner_id(token.getOwner_id());
            }
            if (token.getState() != null) {
                fetched.setState(token.getState());
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
    public String sendToDeliverySiteIdx(Map<String, Object> paramInfo, Integer deliverySiteIdx) {
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
