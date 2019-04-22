package com.mrporter.pomangam.common.util.apiClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentAnnotation;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentResponse;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ImpApi {
    private static final String api_key = "7747339616115849";
    private static final String api_secret = "zUIrMm0z6rjsKg3RTa0pZqlnjxdSEH9eBRGneGXk8gMTxQKq1KQ1bbovICnnjwCVoiiCZgW7wJ13AMuB";

    private static final String domain = "api.iamport.kr";
    private static final boolean isSsl = true;

    public static void main(String...args) {
        prepare("idid1", 34000);
    }

    public static String getToken() {
        ApiClientUtils apiClientUtils = new ApiClientUtils(domain, isSsl);

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("imp_key", api_key);
        body.put("imp_secret", api_secret);
        Object response = apiClientUtils.sendByPostNotBodyList(header, body, "/users/getToken").getBody();

        Gson gson = new Gson();
        AuthResponse res = gson.fromJson(response+"", new TypeToken<AuthResponse>(){}.getType());
        return res.getResponse().getAccess_token();
    }

    public static boolean prepare(String merchant_uid, Integer amount) {
        ApiClientUtils apiClientUtils = new ApiClientUtils(domain, isSsl);

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + getToken());
        header.put("Content-Type", "application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("merchant_uid", merchant_uid);
        body.put("amount", amount);

        ResponseEntity res = apiClientUtils.sendByPostNotBodyList(header, body, "/payments/prepare");
        return res.getStatusCodeValue() == 200;
    }

    public static PaymentAnnotation getInfo(String imp_uid) {
        ApiClientUtils apiClientUtils = new ApiClientUtils(domain, isSsl);

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + getToken());
        header.put("Content-Type", "application/json");

        Map<String, Object> body = new HashMap<>();

        Object response = apiClientUtils.sendByPostNotBodyList(header, body, "/payments/"+imp_uid).getBody();

        Gson gson = new Gson();
        PaymentResponse paymentResponse = gson.fromJson(response+"", new TypeToken<PaymentResponse>(){}.getType());
        return paymentResponse.getResponse();
    }

}
