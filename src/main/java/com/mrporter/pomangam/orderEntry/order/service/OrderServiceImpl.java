package com.mrporter.pomangam.orderEntry.order.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import com.mrporter.pomangam.common.util.choseong.InitialConsonant;
import com.mrporter.pomangam.deliveryEntry.deliverySite.repository.DeliverySiteRepositoryImpl;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.likeForProduct.repository.LikeForProductRepositoryImpl;
import com.mrporter.pomangam.orderEntry.order.domain.*;
import com.mrporter.pomangam.orderEntry.order.repository.OrderJpaRepository;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderItem.repository.OrderItemRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.coupon.domain.Coupon;
import com.mrporter.pomangam.promotionEntry.coupon.repository.CouponJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderJpaRepository orderJpaRepository;
    OrderRepositoryImpl orderRepository;
    OrderItemRepositoryImpl orderItemRepository;
    UserServiceImpl userService;
    DeliverySiteRepositoryImpl deliverySiteRepository;
    DetailForDeliverySiteRepositoryImpl detailForDeliverySiteRepository;
    CouponJpaRepository couponJpaRepository;
    LikeForProductRepositoryImpl likeForProductRepository;

    @Override
    public void setState(Integer order_idx, StateOrder stateOrder) {
        Order order = orderJpaRepository.getOne(order_idx);
        order.setState_order(stateOrder.getCode());
        orderJpaRepository.save(order);
    }

    @Override
    public void setState(String orderId, StateOrder stateOrder) {
        Order order = orderJpaRepository.getByOrderId(orderId);
        order.setState_order(stateOrder.getCode());
        orderJpaRepository.save(order);
    }

    @Override
    public void setReceiptId(Integer order_idx, String receipt_id) {
        Order order = orderJpaRepository.getOne(order_idx);
        order.setReceiptId(receipt_id);
        orderJpaRepository.save(order);
    }

    @Override
    public void deleteByOrderId(String orderId) {
        Order order = orderJpaRepository.getByOrderId(orderId);
        orderJpaRepository.delete(order);
    }

    private List<OrderInfoDto> getOrderInfo(List<OrderDto> list) {
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();

        for(OrderDto order : list) {

            Coupon cp = null;
            Integer cpIdx = order.getUsing_coupon_idx();
            int cpAmount = 0;
            int amount = order.getFinal_amount() == null ? 0 : order.getFinal_amount();
            if(cpIdx != null) {
                cp = couponJpaRepository.getOne(cpIdx);
                cpAmount = amount * (cp.getDiscount_pct() / 100) + cp.getDiscount_prc();
            }

            DetailForDeliverySiteDto detailForDeliverySiteDto = detailForDeliverySiteRepository.getByIdx(order.getDetail_site_idx());
            OrderInfoDto info = OrderInfoDto.builder()
                    .order_idx(order.getIdx())
                    .box_no(InitialConsonant.getInitial(detailForDeliverySiteDto.getAbbreviation())+"-"+order.getBox_no())
                    .delivery_site_name(deliverySiteRepository.getByDeliverySiteIdx(order.getDelivery_site_idx()).getName())
                    .detail_site_name(detailForDeliverySiteDto.getName())
                    .register_date(order.getRegister_date())
                    .type_payment(StatePayment.toString(order.getType_payment()))
                    .state_order(StateOrder.toString(order.getState_order()))
                    .arrival_date_only(order.getArrival_date_only())
                    .arrival_time_only(order.getArrival_time_only())
                    .using_point(order.getUsing_point() == null ? 0 : order.getUsing_point())
                    .using_coupon_name(cp == null ? null : cp.getName())
                    .using_coupon_amount(cpAmount)
                    .final_amount(amount)
                    .saved_point(order.getSaved_point() == null ? 0 : order.getSaved_point())
                    .receipt_id(order.getReceipt_id())
                    .order_id(order.getOrder_id())
                    .orderItems(orderItemRepository.findOrderInfoItem(order.getIdx()))
                    .build();
            orderInfoDtoList.add(info);
        }
        return orderInfoDtoList;
    }

    @Override
    public List<OrderInfoDto> getCurrentOrderInfoByCustomerId(String customerId, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        User user = userService.findById(customerId);
        List<OrderDto> todayList = orderRepository.getTodayOrderByCustomerIdx(user.getIdx(), pageRequest);
        return getOrderInfo(todayList);
    }

    @Override
    public List<OrderInfoDto> getCurrentOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        List<OrderDto> todayList = orderRepository.getTodayOrderByGuestIdx(guestIdx, pageRequest);
        return getOrderInfo(todayList);
    }

    @Override
    public List<OrderInfoDto> getPastOrderInfoByCustomerId(String customerId, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        User user = userService.findById(customerId);
        List<OrderDto> todayList = orderRepository.getPastOrderInfoByCustomerId(user.getIdx(), pageRequest);
        return getOrderInfo(todayList);
    }

    @Override
    public List<OrderInfoDto> getPastOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        List<OrderDto> todayList = orderRepository.getPastOrderInfoByGuestIdx(guestIdx, pageRequest);
        return getOrderInfo(todayList);
    }
}
