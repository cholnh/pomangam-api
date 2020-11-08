package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.bizm.template.OrdeRefundTemplate;
import com.mrporter.pomangam._bases.utils.bootpay.BootpayApi;
import com.mrporter.pomangam._bases.utils.bootpay.model.request.Cancel;
import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.FcmTokenDto;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientToken;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.owner.FcmOwnerTokenJpaRepository;
import com.mrporter.pomangam.client.services.fcm.FcmServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class OrderRefundSubService {

    FcmServiceImpl fcmService;
    FcmClientTokenJpaRepository clientTokenRepo;
    FcmOwnerTokenJpaRepository ownerTokenRepo;
    OwnerJpaRepository ownerRepo;
    private static BootpayApi bootpayApi = new BootpayApi();

    public boolean refundPG(String receipt_id, String name, String reason, Double price) {
        bootpayApi.getAccessToken();
        Cancel cancel = Cancel.builder()
                .receipt_id(receipt_id)
                .name(name)
                .reason(reason)
                .price(price)
                .build();
        System.out.println("PG] cancel: " + cancel);
        return bootpayApi.cancel(cancel);
    }

    public void verifyIsValidRefundOrder(OrderType orderType) {
        boolean isValid = true;
        switch (orderType) {
            case PAYMENT_REFUND:
            case PAYMENT_CANCEL:
            case PAYMENT_FAIL:
            case PAYMENT_READY_FAIL_PROMOTION:
            case PAYMENT_READY_FAIL_COUPON:
            case PAYMENT_READY_FAIL_POINT:
            case PAYMENT_READY:
                isValid = false;
                break;
        }
        if(!isValid) {
            throw  new OrderException("invalid order refund.");
        }
    }

    public void sendFcm(Order order) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", "order_refund");

            List<FcmTokenDto> to = new ArrayList<>();
            FcmClientToken clientToken = clientTokenRepo.findByIdxAndIsActiveIsTrue(order.getOrderer().getIdxFcmToken());
            if(clientToken != null) {
                to.add(FcmTokenDto.builder()
                        .token(clientToken.getToken())
                        .build());

                // fcm build
                FcmRequestDto dto = FcmRequestDto.builder()
                        .title("(" + order.getBoxNumber() + "번) 환불 요청이 성공적으로 접수되었습니다.")
                        .data(data)
                        .to(to)
                        .build();
                fcmService.send(dto);
            }
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

    public void sendKakaoAT(Order order) {
        try {
            if(order.getOrderer().getOrdererType() == OrdererType.GUEST) return;

            Map<String, String> data = new HashMap<>();

            data.put("order_idx", order.getBoxNumber() + "번");
            data.put("order_date", order.getDeliveryDetailSite().getFullName());
            data.put("order_items", CommonSubService.getOrderDate(order));
            data.put("order_refund_price", order.paymentCost() + "원");
            OrdeRefundTemplate.send(PhoneNumberFormatter.format(order.getOrderer().getUser().getPhoneNumber()), data);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }
}
