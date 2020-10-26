package com.mrporter.pomangam._bases.utils.bizm.template;

import com.mrporter.pomangam._bases.utils.bizm.BizmApi;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class OrderDisapproveTemplate {
    private final static String msg1 = "(주문 실패 안내) 가게 사정으로 인해 주문이 취소되었습니다.";
    private final static String msg2 = "\n■ 주문번호 : ";
    private final static String msg3 = "\n■ 주문날짜 : ";
    private final static String msg4 = "\n■ 주문내역 : ";
    private final static String msg5 = "\n■ 취소사유 : ";
    private final static String tmplId = "pmg_order_disapprove_1";

    public static ResponseEntity<?> send(String phoneNumber, Map<String, String> data) {
//        System.out.println("bizm : [phoneNumber : " + phoneNumber + " // tmplId : " + tmplId + "]");
//        System.out.println((
//                msg1 +
//                msg2 + data.get("order_idx") +
//                msg3 + data.get("order_date") +
//                msg4 + data.get("order_items") +
//                msg5 + data.get("reason"))
//        );
//        return null;
        return BizmApi.send(phoneNumber, (
                msg1 +
                msg2 + data.get("order_idx") +
                msg3 + data.get("order_date") +
                msg4 + data.get("order_items") +
                msg5 + data.get("reason")
            ), tmplId);
    }

    public static void send(List<String> phoneNumbers, Map<String, String> data) {
        for(String phoneNumber : phoneNumbers) {
            send(phoneNumber, data);
        }
    }
}
