package com.mrporter.pomangam.client.domains.order;

import com.mrporter.pomangam.client.domains.coupon.Coupon;
import com.mrporter.pomangam.client.domains.coupon.CouponDto;
import com.mrporter.pomangam.client.domains.coupon.CouponMapper;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.order.item.OrderItem;
import com.mrporter.pomangam.client.domains.order.item.OrderItemResponseDto;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.order.payment_info.PaymentInfo;
import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.promotion.Promotion;
import com.mrporter.pomangam.client.domains.promotion.PromotionDto;
import com.mrporter.pomangam.client.domains.promotion.PromotionMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponseDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    // 주문 기본 정보
    private OrderType orderType;
    private Short boxNumber;
    private PaymentType paymentType;
    private OrdererType ordererType;

    // 결제 정보
    private Integer usingPoint;
    private List<CouponDto> usingCoupons = new ArrayList<>();
    private List<PromotionDto> usingPromotions = new ArrayList<>();
    private Integer savedPoint;
    private String cashReceipt;
    private Integer totalCost;
    private Integer discountCost;
    private Integer paymentCost;

    // 받는 장소
    private Long idxDeliverySite;
    private Long idxDeliveryDetailSite;
    private String nameDeliverySite;
    private String nameDeliveryDetailSite;

    // 받는 시간
    private Long idxOrderTime;
    private LocalTime arrivalTime;
    private LocalTime additionalTime;

    List<OrderItemResponseDto> orderItems = new ArrayList<>();

    public static OrderResponseDto fromEntity(Order entity) {
        if(entity == null) return null;
        OrderResponseDto dto = new ModelMapper().map(entity, OrderResponseDto.class);

        PaymentInfo paymentInfo = entity.getPaymentInfo();

        // 주문 타입
        dto.setPaymentType(paymentInfo.getPayment().getPaymentType());

        // 주문자 타입
        dto.setOrdererType(entity.getOrderer().getOrdererType());

        // 쿠폰
        List<CouponMapper> couponMappers = paymentInfo.getUsingCoupons();
        dto.setUsingCoupons(convertCoupons(couponMappers));

        // 프로모션
        List<PromotionMapper> promotionMappers = paymentInfo.getUsingPromotions();
        dto.setUsingPromotions(convertPromotions(promotionMappers));

        // 총 주문 가격
        dto.setTotalCost(entity.totalCost());

        // 총 할인 가격
        dto.setDiscountCost(entity.discountCost());

        // 결제 금액
        dto.setPaymentCost(entity.paymentCost());

        // 적립 포인트
        dto.setSavedPoint(paymentInfo.getSavedPoint());

        // 현금영수증
        dto.setCashReceipt(paymentInfo.getCashReceipt());

        // 받는 장소
        DeliveryDetailSite deliveryDetailSite = entity.getDeliveryDetailSite();
        DeliverySite deliverySite = deliveryDetailSite.getDeliverySite();
        dto.setIdxDeliverySite(deliverySite.getIdx());
        dto.setIdxDeliveryDetailSite(deliveryDetailSite.getIdx());
        dto.setNameDeliverySite(deliverySite.getName());
        dto.setNameDeliveryDetailSite(deliveryDetailSite.getName());

        // 받는 시간
        OrderTime orderTime = entity.getOrderTime();
        dto.setIdxOrderTime(orderTime.getIdx());
        dto.setArrivalTime(orderTime.getArrivalTime());
        dto.setAdditionalTime(deliveryDetailSite.getAdditionalTime());

        // 주문 내역
        dto.setOrderItems(convertOrderItems(entity.getOrderItems()));

        return dto;
    }

    public static List<OrderResponseDto> fromEntities(List<Order> entities) {
        if(entities == null) return null;
        List<OrderResponseDto> dtos = new ArrayList<>();
        for(Order entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

    private static List<CouponDto> convertCoupons(List<CouponMapper> couponMappers) {
        List<CouponDto> couponDtos = new ArrayList<>();
        if(couponMappers != null) {
            for(CouponMapper couponMapper : couponMappers) {
                Coupon coupon = couponMapper.getCoupon();
                if(coupon != null) {
                    couponDtos.add(CouponDto.fromEntity(coupon));
                }
            }
        }
        return couponDtos;
    }

    private static List<PromotionDto> convertPromotions(List<PromotionMapper> promotionMappers) {
        List<PromotionDto> promotionDtos = new ArrayList<>();
        if(promotionMappers != null) {
            for(PromotionMapper promotionMapper : promotionMappers) {
                Promotion promotion = promotionMapper.getPromotion();
                if(promotion != null) {
                    promotionDtos.add(PromotionDto.fromEntity(promotion));
                }
            }
        }
        return promotionDtos;
    }


    private static List<OrderItemResponseDto> convertOrderItems(List<OrderItem> orderItems) {
        List<OrderItemResponseDto> orderItemResponseDtos = new ArrayList<>();
        if(orderItems != null) {
            for(OrderItem orderItem : orderItems) {
                if(orderItem != null) {
                    orderItemResponseDtos.add(OrderItemResponseDto.fromEntity(orderItem));
                }
            }
        }
        return orderItemResponseDtos;
    }

}
