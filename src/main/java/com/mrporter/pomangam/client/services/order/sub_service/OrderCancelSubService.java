package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.bizm.template.OrderCancelTemplate;
import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.FcmTokenDto;
import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerToken;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.owner.FcmOwnerTokenJpaRepository;
import com.mrporter.pomangam.client.services.fcm.FcmServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class OrderCancelSubService {

    FcmServiceImpl fcmService;
    FcmClientTokenJpaRepository clientTokenRepo;
    FcmOwnerTokenJpaRepository ownerTokenRepo;
    OwnerJpaRepository ownerRepo;

    public void verifyIsValidCancelOrder(OrderType orderType) {
        boolean isValid = false;
        switch (orderType) {
            case PAYMENT_SUCCESS:
            case ORDER_READY:
            case ORDER_QUICK_READY:
                isValid = true;
                break;
        }
        if(!isValid) {
            throw  new OrderException("invalid order cancel.");
        }
    }

    public void verifyIsValidFailOrder(OrderType orderType) {
        boolean isValid = false;
        switch (orderType) {
            case PAYMENT_READY:
                isValid = true;
                break;
        }
        if(!isValid) {
            throw  new OrderException("invalid order fail.");
        }
    }


    public void sendFcm(Order order) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", "order_cancel");

            // 업체별 전송할 token 정리
            List<FcmTokenDto> to = new ArrayList<>();
            for(Long idxStore : CommonSubService.getIdxStores(order)) {
                List<FcmOwnerToken> ownerTokens = ownerTokenRepo.findByStore(idxStore);
                for(FcmOwnerToken ownerToken : ownerTokens) {
                    if(ownerToken != null) {
                        to.add(FcmTokenDto.builder()
                                .token(ownerToken.getToken())
                                .build());
                    }
                }
            }

            // fcm build
            FcmRequestDto dto = FcmRequestDto.builder()
                    .title("(주문취소알림) " + CommonSubService.getOrderTime(order) + " " + order.getBoxNumber() + "번 주문이 취소되었습니다.")
                    //.body("[no." + order.getIdx() + "] " + CommonSubService.orderItemShortText(order))
                    .body("")
                    .data(data)
                    .to(to)
                    .build();
            fcmService.send(dto);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

    public void sendKakaoAT(Order order) {
        try {
            for(Long idxStore : CommonSubService.getIdxStores(order)) {
                List<Owner> owners = ownerRepo.findByIdxStoreAndIsActiveIsTrue(idxStore);
                for(Owner owner : owners) {
                    Map<String, String> data = new HashMap<>();
                    data.put("order_date", CommonSubService.getOrderDateWithAdditionalTime(order));
                    data.put("order_bn", CommonSubService.getOrderTime(order) + " " + order.getBoxNumber());
                    data.put("order_idx", "no." + order.getIdx() + " (" + order.getBoxNumber() + "번)");
                    data.put("order_addr", order.getDeliveryDetailSite().getFullName());
                    data.put("order_pn", CommonSubService.getOrdererPhoneNumber(order));
                    data.put("order_items", CommonSubService.orderItemLongText(order, idxStore));

                    OrderCancelTemplate.send(owner.getPhoneNumber(), data);
                }
            }


        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }
}
