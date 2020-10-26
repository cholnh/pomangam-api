package com.mrporter.pomangam._bases.utils.bizm.template;

import com.mrporter.pomangam._bases.utils.bizm.BizmApi;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class VBankDepositTemplate {
    private final static String msg1 = "[입금 내역 불일치]";

    private final static String msg2 = "\n입금자명 : ";
    private final static String msg3 = "\n입금액 : ";
    private final static String msg4 = "\ntid : ";
    private final static String msg5 = "\n상세 원인 : ";
    private final static String tmplId = "pmg_admin_deposit_1";

    public static ResponseEntity<?> send(String phoneNumber, Map<String, String> data, Map<String, Object> button) {
        return BizmApi.sendWithButton(phoneNumber, (
                msg1 +
                msg2 + data.get("name") +
                msg3 + data.get("input") +
                msg4 + data.get("tid") +
                msg5 + data.get("cause")
            ), tmplId, null, button);
    }

    public static void send(List<String> phoneNumbers, Map<String, String> data, Map<String, Object> button) {
        for(String phoneNumber : phoneNumbers) {
            send(phoneNumber, data, button);
        }
    }
}
