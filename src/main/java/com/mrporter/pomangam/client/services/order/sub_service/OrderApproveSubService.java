package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.bizm.template.OrderApproveTemplate;
import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.FcmTokenDto;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientToken;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import com.mrporter.pomangam.client.services.fcm.FcmServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class OrderApproveSubService {

    FcmServiceImpl fcmService;
    FcmClientTokenJpaRepository clientTokenRepo;

    public void sendFcm(Order order) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", "order_approve");

            List<FcmTokenDto> to = new ArrayList<>();
            FcmClientToken clientToken = clientTokenRepo.findByIdxAndIsActiveIsTrue(order.getOrderer().getIdxFcmToken());
            if(clientToken != null) {
                to.add(FcmTokenDto.builder()
                        .token(clientToken.getToken())
                        .build());
                // fcm build
                FcmRequestDto dto = FcmRequestDto.builder()
                        .title("(" + order.getBoxNumber() + "번) 주문이 정상적으로 접수되었습니다.")
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

            data.put("order_bn", order.getBoxNumber() + "번");
            data.put("order_addr", order.getDeliveryDetailSite().getFullName());
            data.put("order_date", CommonSubService.getOrderDate(order));
            data.put("order_items", CommonSubService.orderItemLongText(order));
            OrderApproveTemplate.send(PhoneNumberFormatter.format(order.getOrderer().getUser().getPhoneNumber()), data);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }
}
