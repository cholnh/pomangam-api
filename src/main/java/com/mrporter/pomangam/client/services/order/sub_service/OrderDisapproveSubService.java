package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.bizm.template.OrderDisapproveTemplate;
import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.FcmTokenDto;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientToken;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import com.mrporter.pomangam.client.services.fcm.FcmServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class OrderDisapproveSubService {

    FcmServiceImpl fcmService;
    FcmClientTokenJpaRepository clientTokenRepo;

    public void verifyIsValidDisapproveOrder(OrderType orderType) {
        boolean isValid = false;
        switch (orderType) {
            case PAYMENT_READY:
            case PAYMENT_READY_FAIL_POINT:
            case PAYMENT_READY_FAIL_COUPON:
            case PAYMENT_READY_FAIL_PROMOTION:
            case PAYMENT_SUCCESS:
            case ORDER_READY:
            case ORDER_QUICK_READY:
            case ORDER_SUCCESS:
            case DELIVERY_READY:
            case DELIVERY_DELAY:
            case DELIVERY_PICKUP:
            case DELIVERY_SUCCESS:
                isValid = true;
                break;
        }
        if(!isValid) {
            throw  new OrderException("invalid order disapprove.");
        }
    }

    public void sendFcm(Order order, String reason) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", "order_disapprove");

            List<FcmTokenDto> to = new ArrayList<>();
            FcmClientToken clientToken = clientTokenRepo.findByIdxAndIsActiveIsTrue(order.getOrderer().getIdxFcmToken());
            to.add(FcmTokenDto.builder()
                    .token(clientToken.getToken())
                    .build());

            // fcm build
            FcmRequestDto dto = FcmRequestDto.builder()
                    .title("(주문 실패 안내) 가게 사정으로 인해 주문이 취소되었습니다.")
                    .body(reason)
                    .data(data)
                    .to(to)
                    .build();
            fcmService.send(dto);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

    public void sendKakaoAT(Order order, String reason) {
        try {
            if(order.getOrderer().getOrdererType() == OrdererType.GUEST) return;

            Map<String, String> data = new HashMap<>();

            data.put("order_idx", "no." + order.getIdx());
            data.put("order_date", CommonSubService.getOrderDate(order));
            data.put("order_items", CommonSubService.orderItemLongText(order));
            data.put("reason", reason);
            OrderDisapproveTemplate.send(PhoneNumberFormatter.format(order.getOrderer().getUser().getPhoneNumber()), data);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }
}
