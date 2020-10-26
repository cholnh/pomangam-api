package com.mrporter.pomangam._bases.utils.bizm.template;

import com.mrporter.pomangam._bases.utils.bizm.BizmApi;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class OrderCancelTemplate {
    private final static String msg1 = "(주문취소알림) ";
    private final static String msg2 = "번 주문이 취소되었습니다.";
    private final static String msg3 = "\n■ 주문번호 : ";
    private final static String msg4 = "\n■ 받는장소 : ";
    private final static String msg5 = "\n■ 받는시간 : ";
    private final static String msg6 = "\n■ 고객번호 : ";
    private final static String msg7 = "\n■ 주문내역 : ";
    private final static String tmplId = "pmg_store_order_cancel_1";

    public static ResponseEntity<?> send(String phoneNumber, Map<String, String> data) {
//        System.out.println("bizm : [phoneNumber : " + phoneNumber + " // tmplId : " + tmplId + "]");
//        System.out.println((
//                msg1 + data.get("order_bn") + msg2 +
//                msg3 + data.get("order_idx") +
//                msg4 + data.get("order_addr") +
//                msg5 + data.get("order_date") +
//                msg6 + data.get("order_pn") +
//                msg7 + data.get("order_items")
//        ));
//        return null;
        return BizmApi.send(phoneNumber, (
                msg1 + data.get("order_bn") + msg2 +
                msg3 + data.get("order_idx") +
                msg4 + data.get("order_addr") +
                msg5 + data.get("order_time") +
                msg6 + data.get("order_pn") +
                msg7 + data.get("order_items")
            ), tmplId);
    }

    public static void send(List<String> phoneNumbers, Map<String, String> data) {
        for(String phoneNumber : phoneNumbers) {
            send(phoneNumber, data);
        }
    }
}
