package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.bizm.template.OrdeDelayTemplate;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DeliveryDelaySubService {

    FcmServiceImpl fcmService;
    FcmClientTokenJpaRepository clientTokenRepo;

    public void sendFcm(Order order, int min, String reason) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", "order_delay");

            List<FcmTokenDto> to = new ArrayList<>();
            FcmClientToken clientToken = clientTokenRepo.findByIdxAndIsActiveIsTrue(order.getOrderer().getIdxFcmToken());
            if(clientToken != null) {
                to.add(FcmTokenDto.builder()
                        .token(clientToken.getToken())
                        .build());

                // fcm build
                FcmRequestDto dto = FcmRequestDto.builder()
                        .title("(배달 지연 안내) 배달 도착시간이 약 " + min + "분 간 지연되어 안내드립니다.")
                        .body(reason)
                        .data(data)
                        .to(to)
                        .build();
                fcmService.send(dto);
            }
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

    public void sendKakaoAT(Order order, int min, String reason) {
        try {
            if(order.getOrderer().getOrdererType() == OrdererType.GUEST) return;

            Map<String, String> data = new HashMap<>();

            data.put("order_delay", min + "분");
            data.put("order_bn", order.getBoxNumber() + "번");
            data.put("order_idx", "no." + order.getIdx());
            data.put("order_delay_reason", reason);
            data.put("order_eta", eta(order, min));
            OrdeDelayTemplate.send(PhoneNumberFormatter.format(order.getOrderer().getUser().getPhoneNumber()), data);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

    private String eta(Order order, int min) {
        LocalTime time = order.getOrderTime().getArrivalTime()
                .plusMinutes(order.getDeliveryDetailSite().getAdditionalTime().getMinute())
                .plusMinutes(min);
        return time.getHour() + "시" + (time.getMinute() == 0 ? "" : " " + time.getMinute() + "분");
    }
}
