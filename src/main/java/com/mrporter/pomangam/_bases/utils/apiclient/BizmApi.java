package com.mrporter.pomangam._bases.utils.apiclient;

        import org.springframework.http.ResponseEntity;

        import java.util.HashMap;
        import java.util.Map;

public class BizmApi {
    private static final String userId = "mrporter";
    private static final String message_type = "at";
    private static final String profile = "4b347dc9844c7540e81851bc41a5ea9f5e985b38";
    private static final String domain = "alimtalk-api.bizmsg.kr";
    private static final String subUrl = "/v2/sender/send";
    private static final boolean isSsl = true;

    public static ResponseEntity<?> send(String phn, String msg, String tmplId) {
        ApiClientUtils apiClientUtils = new ApiClientUtils(domain, isSsl);

        Map<String, String> header = new HashMap<>();
        header.put("userId", userId);

        Map<String, Object> body = new HashMap<>();
        body.put("message_type", message_type);
        body.put("phn", phn);
        body.put("profile", profile);
        body.put("msg", msg);
        body.put("tmplId", tmplId);
        return apiClientUtils.sendByPost(header, body, subUrl);
    }
}
