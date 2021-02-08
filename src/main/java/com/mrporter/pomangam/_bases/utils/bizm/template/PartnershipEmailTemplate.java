package com.mrporter.pomangam._bases.utils.bizm.template;

import com.mrporter.pomangam._bases.utils.bizm.BizmApi;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class PartnershipEmailTemplate {
    private final static String msg1 = "[신규 제휴문의]";

    private final static String msg2 = "\n성함 : ";
    private final static String msg3 = "\n이메일 : ";
    private final static String msg4 = "\n내용 : ";
    private final static String tmplId = "pmg_partnership_email_1";

    public static ResponseEntity<?> send(String phoneNumber, Map<String, String> data) {
        return BizmApi.send(phoneNumber, (
                msg1 +
                msg2 + data.get("name") +
                msg3 + data.get("email") +
                msg4 + data.get("contents")
            ), tmplId, null);
    }

    public static void send(List<String> phoneNumbers, Map<String, String> data) {
        for(String phoneNumber : phoneNumbers) {
            send(phoneNumber, data);
        }
    }
}
