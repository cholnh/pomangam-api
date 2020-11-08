package com.mrporter.pomangam.client.services.order.sub_service;

import com.mrporter.pomangam._bases.utils.time.CustomTime;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.item.OrderItem;
import com.mrporter.pomangam.client.domains.order.log.OrderLog;
import com.mrporter.pomangam.client.domains.order.orderer.Orderer;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import com.mrporter.pomangam.client.domains.user.coupon.CouponMapper;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderLogJpaRepository;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponJpaRepository;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponMapperJpaRepository;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import com.mrporter.pomangam.client.services.user.point.log.PointLogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class CommonSubService {

    OrderJpaRepository orderRepo;
    OrderLogJpaRepository orderLogRepo;
    UserServiceImpl userService;
    CouponJpaRepository couponRepo;
    CouponMapperJpaRepository couponMapperRepo;
    PointLogServiceImpl pointLogService;

    public void log(Long idxOrder, OrderType...orderTypes) {
        if(orderTypes == null || orderTypes.length == 0) return;

        // log 상태 변경
        List<OrderLog> orderLogs = new ArrayList<>();
        for(OrderType orderType : orderTypes) {
            orderLogs.add(OrderLog.builder()
                    .idxOrder(idxOrder)
                    .orderType(orderType)
                    .build());
        }
        orderLogRepo.saveAll(orderLogs);

        // order 상태 변경
        Order order = orderRepo.findById(idxOrder)
                .orElseThrow(() -> new RuntimeException("invalid order"));
        order.setOrderType(orderTypes[orderTypes.length-1]); // 최종 상태
        orderRepo.save(order);
    }

    public void verifyUsingPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            int userPoint = pointLogService.findByIdxUser(user.getIdx());
            int usingPoint = order.getPaymentInfo().getUsingPoint();
            if(usingPoint == 0) return;
            if(userPoint < usingPoint || usingPoint < 0) {
                log(order.getIdx(), OrderType.PAYMENT_READY_FAIL_POINT);
                throw new OrderException("invalid using point.");
            }
            userService.minusPointByIdx(user.getIdx(), usingPoint, PointType.USED_BY_BUY, order.getIdx());
        }
    }

    public void verifyUsingCoupons(Order order, Set<Long> idxesUsingCoupons) {
        User user = order.getOrderer().getUser();
        if(user != null && idxesUsingCoupons != null && !idxesUsingCoupons.isEmpty()) {
            List<CouponMapper> couponMappers = new ArrayList<>();
            List<Coupon> userCoupons = couponRepo.findByUser_IdxAndIsActiveIsTrue(user.getIdx());
            for(Long idxUsingCoupon : idxesUsingCoupons) {
                Coupon coupon = findCoupon(userCoupons, idxUsingCoupon);
                if(coupon == null || !coupon.isValid()) {
                    log(order.getIdx(), OrderType.PAYMENT_READY_FAIL_COUPON);
                    throw new OrderException("invalid using coupon.");
                }
                coupon.setIsUsed(true);
                couponMappers.add(CouponMapper.builder()
                        .order(order)
                        .coupon(coupon)
                        .build());
            }
            couponMapperRepo.saveAll(couponMappers);
            //order.getPaymentInfo().getUsingCoupons().addAll(couponMappers);
        }
    }

    public void verifyUsingCouponCode(Order order, String couponCode) {
        if(couponCode != null) {
            Optional<Coupon> optional = couponRepo.findByCodeAndIsActiveIsTrueAndUserIsNull(couponCode);
            if(optional.isPresent() && optional.get().isValid()) {
                Coupon coupon = optional.get();
                coupon.setIsUsed(true);
                CouponMapper mapper = CouponMapper.builder()
                        .order(order)
                        .coupon(coupon)
                        .build();
                couponMapperRepo.save(mapper);
                //order.getPaymentInfo().getUsingCoupons().add(mapper);
            } else {
                log(order.getIdx(), OrderType.PAYMENT_READY_FAIL_COUPON);
                throw new OrderException("invalid coupon code.");
            }
        }
    }

    public void verifyUsingPromotions(Order order, Set<Long> idxesUsingPromotions) {
        // Todo. 프로모션 개발
    }

    public void verifySavedPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            float percentSavePoint = user.getPointRank().getPercentSavePoint();
            int priceSavePoint = user.getPointRank().getPriceSavePoint();
            int paymentCost = order.paymentCost();
            int savedPoint = (int)(paymentCost * percentSavePoint / 100) + priceSavePoint;
            order.getPaymentInfo().setSavedPoint(savedPoint);
            orderRepo.save(order);
        }
    }

    public Coupon findCoupon(List<Coupon> userCoupons, Long findIdx) {
        for(Coupon userCoupon : userCoupons) {
            if(findIdx.compareTo(userCoupon.getIdx()) == 0) {
                return userCoupon;
            }
        }
        return null;
    }

    public void commitSavedPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            int savedPoint = order.getPaymentInfo().getSavedPoint();
            userService.plusPointByIdx(user.getIdx(), savedPoint, PointType.ISSUED_BY_BUY, order.getIdx());
        }
    }

    public void rollbackUsingPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            int usingPoint = order.getPaymentInfo().getUsingPoint();
            if(usingPoint > 0) {
                userService.plusPointByIdx(user.getIdx(), usingPoint, PointType.ROLLBACK_ISSUED_BY_PAYMENT_CANCEL, order.getIdx());
            }
        }
    }

    public void rollbackSavedPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            int savedPoint = order.getPaymentInfo().getSavedPoint();
            if(savedPoint > 0) {
                userService.minusPointByIdx(user.getIdx(), savedPoint, PointType.ROLLBACK_SAVED_BY_PAYMENT_CANCEL, order.getIdx());
            }
        }
    }

    public void rollbackUsingCoupons(Order order) {
        List<CouponMapper> couponMappers = couponMapperRepo.findByOrder_Idx(order.getIdx());
        for(CouponMapper couponMapper : couponMappers) {
            Coupon coupon = couponMapper.getCoupon();
            coupon.setIsUsed(false);
            couponRepo.save(coupon);
            couponMapperRepo.delete(couponMapper);
            order.getPaymentInfo().usingCouponsClear(); // order 쪽도 변경해줘야 함.
        }
    }

    public static Set<Long> getIdxStores(Order order) {
        Set<Long> idxStores = new HashSet<>();
        for(OrderItem item : order.getOrderItems()) {
            idxStores.add(item.getStore().getIdx());
        }
        return idxStores;
    }

    public static String getOrderTime(Order order) {
        return CustomTime.format("HH:mm", order.getModifyDate());
    }

    public static String getOrderDate(Order order) {
        return CustomTime.format("yyyy/MM/dd HH:mm", order.getModifyDate());
    }

    public static String getOrdererPhoneNumber(Order order) {
        Orderer orderer = order.getOrderer();
        return orderer.getOrdererType() == OrdererType.USER
                ? orderer.getUser().getPhoneNumber()
                : "미표기";
    }

    public static String orderItemLongText(Order order) {
        return orderItemLongText(order, null);
    }

    public static String orderItemLongText(Order order, Long idxStore) {
        List<OrderItem> items = order.getOrderItems();
        List<OrderItem> storeItems;

        if(idxStore == null) {
            storeItems = items;
        } else {
            storeItems = new ArrayList<>();
            for(OrderItem item : items) {
                if(item.getStore().getIdx().intValue() == idxStore.intValue()) {
                    storeItems.add(item);
                }
            }
        }

        String text = storeItems.get(0).getProduct().getProductInfo().getName();
        int size = storeItems.size();
        if(size == 1) {
            if(storeItems.get(0).getQuantity() != 1) {
                text += " " + storeItems.get(0).getQuantity() + "개";
            }
        } else if(size > 1) {
            text += " 외 " + (storeItems.size() - 1) + "개";
        } else {
            text = "오류";
        }
        return text;
    }
}
