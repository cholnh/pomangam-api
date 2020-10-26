package com.mrporter.pomangam._bases.utils.bizm.template;

import com.mrporter.pomangam._bases.utils.bizm.BizmApi;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class OrdeDepositTemplate {
    private final static String msg1_1 = "[포만감 입금확인 안내]\n";
    private final static String msg1_2 = "님의 결제가 정상적으로 처리되어,\n";
    private final static String msg1_3 = "주문내역이 음식점으로 전달되었습니다.\n";

    private final static String msg2 = "\n■ 주문번호 : ";
    private final static String msg3 = "\n■ 결제금액 : ";
    private final static String tmplId = "pmg_deposit_client_1";

    public static ResponseEntity<?> send(String phoneNumber, Map<String, String> data) {
//        System.out.println("bizm : [phoneNumber : " + phoneNumber + " // tmplId : " + tmplId + "]");
//        System.out.println((
//                msg1_1 +
//                data.get("order_user") + msg1_2 +
//                msg1_3 +
//                msg2 + data.get("order_idx") +
//                msg3 + data.get("order_price"))
//        );
//        return null;
        return BizmApi.send(phoneNumber, (
                msg1_1 +
                data.get("order_user") + msg1_2 +
                msg1_3 +
                msg2 + data.get("order_idx") +
                msg3 + data.get("order_price")
            ), tmplId);
    }

    public static void send(List<String> phoneNumbers, Map<String, String> data) {
        for(String phoneNumber : phoneNumbers) {
            send(phoneNumber, data);
        }
    }
}
