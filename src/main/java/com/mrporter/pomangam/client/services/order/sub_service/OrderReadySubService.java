package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.bizm.template.OrderReadyTemplate;
import com.mrporter.pomangam._bases.utils.bootpay.BootpayApi;
import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.FcmTokenDto;
import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerToken;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSub;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.repositories.fcm.owner.FcmOwnerTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
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
public class OrderReadySubService{

    FcmServiceImpl fcmService;
    StoreJpaRepository storeRepo;
    ProductJpaRepository productRepo;
    ProductSubJpaRepository productSubRepo;
    FcmOwnerTokenJpaRepository ownerTokenRepo;
    OwnerJpaRepository ownerRepo;
    private static BootpayApi bootpayApi = new BootpayApi();

    public void verifyIsValidVerifyOrder(OrderType orderType) {
        boolean isValid = false;
        switch (orderType) {
            case PAYMENT_READY:
            case PAYMENT_READY_FAIL_POINT:
            case PAYMENT_READY_FAIL_COUPON:
            case PAYMENT_READY_FAIL_PROMOTION:
            case PAYMENT_SUCCESS:
                isValid = true;
                break;
        }

        if(!isValid) {
            throw  new OrderException("invalid order verify.");
        }
    }

    public boolean verifyPG(String receipt_id, int paymentCost) {
        bootpayApi.getAccessToken();
        return bootpayApi.verify(receipt_id, paymentCost);
    }

    public void addCntOrder(Order order) {
        Map<Long, Short> map = new HashMap<>();
        order.getOrderItems().forEach((item) -> {
            short quantity = item.getQuantity();

            // storeMapper
            if(map.containsKey(item.getStore().getIdx())) {
                quantity += map.get(item.getStore().getIdx());
            }
            map.put(item.getStore().getIdx(), quantity);

            // productMapper
            Product product = productRepo.findByIdxAndIsActiveIsTrue(item.getProduct().getIdx());
            product.setCntOrder(product.getCntOrder() + Short.toUnsignedInt(quantity));
            productRepo.save(product);

            // productMapper sub
            for(OrderItemSub sub : item.getOrderItemSubs()) {
                ProductSub productSub = productSubRepo.findByIdxAndIsActiveIsTrue(sub.getIdx());
                productSub.setCntOrder(productSub.getCntOrder() + Short.toUnsignedInt(sub.getQuantity()));
                productSubRepo.save(productSub);

            }
        });
        map.forEach((k, v) -> {
            Store store = storeRepo.findByIdxAndIsActiveIsTrue(k);
            store.setCntOrder(store.getCntOrder() + Short.toUnsignedInt(v));
            storeRepo.save(store);
        });
    }

    public void sendFcm(Order order) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("type", "order_ready");

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
                    .title(CommonSubService.getOrderTime(order) + " (" + order.getBoxNumber() + "번) 새로운 주문이 등록되었습니다.")
                    //.body("[no." + order.getIdx() + "] " + CommonSubService.orderItemLongText(order))
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
                    data.put("order_time", CommonSubService.getOrderTime(order));
                    data.put("order_idx", "no."+order.getIdx());
                    data.put("order_bn", order.getBoxNumber() + "번");
                    data.put("order_date", CommonSubService.getOrderDate(order));
                    data.put("order_addr", order.getDeliveryDetailSite().getFullName());
                    data.put("order_pn", CommonSubService.getOrdererPhoneNumber(order));
                    data.put("order_items", CommonSubService.orderItemLongText(order, idxStore));
                    OrderReadyTemplate.send(owner.getPhoneNumber(), data);
                }
            }
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

}
