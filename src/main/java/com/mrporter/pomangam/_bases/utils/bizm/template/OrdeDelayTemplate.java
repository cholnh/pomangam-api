package com.mrporter.pomangam._bases.utils.bizm.template;

import com.mrporter.pomangam._bases.utils.bizm.BizmApi;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class OrdeDelayTemplate {
    private final static String msg1_1 = "(배달 지연 안내) 배달 도착시간이 약 ";
    private final static String msg1_2 = " 간 지연되어 안내드립니다.";

    private final static String msg2 = "\n■ 식별번호 : ";
    private final static String msg3 = "\n■ 주문번호 : ";
    private final static String msg4 = "\n■ 지연사유 : ";
    private final static String msg5 = "\n■ 예상도착시간 : ";
    private final static String tmplId = "pmg_order_delay_1";

    public static ResponseEntity<?> send(String phoneNumber, Map<String, String> data) {
//        System.out.println("bizm : [phoneNumber : " + phoneNumber + " // tmplId : " + tmplId + "]");
//        System.out.println((
//                msg1_1 + data.get("order_delay") + msg1_2 +
//                msg2 + data.get("order_bn") +
//                msg3 + data.get("order_idx") +
//                msg4 + data.get("order_delay_reason") +
//                msg5 + data.get("order_eta"))
//        );
//        return null;
        return BizmApi.send(phoneNumber, (
                msg1_1 + data.get("order_delay") + msg1_2 +
                msg2 + data.get("order_bn") +
                msg3 + data.get("order_idx") +
                msg4 + data.get("order_delay_reason") +
                msg5 + data.get("order_eta")
            ), tmplId);
    }

    public static void send(List<String> phoneNumbers, Map<String, String> data) {
        for(String phoneNumber : phoneNumbers) {
            send(phoneNumber, data);
        }
    }
}
