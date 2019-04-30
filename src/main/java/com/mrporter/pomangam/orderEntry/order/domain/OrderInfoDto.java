package com.mrporter.pomangam.orderEntry.order.domain;

import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderInfoItemDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderInfoDto implements Serializable {

    private Integer order_idx;  // 주문 번호

    private String box_no;     // bn

    private String delivery_site_name;  // 배달 장소

    private String detail_site_name;    // 배달 장소 상세

    private Timestamp register_date;    // 주문 일시

    private String type_payment;  // 결제 수단 (카드결제 : 1 / 가상계좌 : 2 / 핸드폰결제 : 3 / 카카오 페이 : 4)

    private String state_order;   // 주문 상태 (StateOrder 변환)

    private Date arrival_date_only; // 배달 시간

    private Time arrival_time_only; // 배달 시간

    private Integer using_point;    // 사용 포인트

    private String using_coupon_name;   // 사용 쿠폰 이름

    private Integer using_coupon_amount;   // 사용 쿠폰 가격

    private Integer final_amount;   // 최종 총 가격

    private Integer saved_point;

    private String order_id;

    private String receipt_id;

    private String phone;

    List<OrderInfoItemDto> orderItems;  // 주문 제품 목록

    @Builder
    public OrderInfoDto(Integer order_idx, String box_no, String delivery_site_name, String detail_site_name, Timestamp register_date, String type_payment, String state_order, Date arrival_date_only, Time arrival_time_only, Integer using_point, String using_coupon_name, Integer using_coupon_amount, Integer final_amount, Integer saved_point, String order_id, String receipt_id, String phone, List<OrderInfoItemDto> orderItems) {
        this.order_idx = order_idx;
        this.box_no = box_no;
        this.delivery_site_name = delivery_site_name;
        this.detail_site_name = detail_site_name;
        this.register_date = register_date;
        this.type_payment = type_payment;
        this.state_order = state_order;
        this.arrival_date_only = arrival_date_only;
        this.arrival_time_only = arrival_time_only;
        this.using_point = using_point;
        this.using_coupon_name = using_coupon_name;
        this.using_coupon_amount = using_coupon_amount;
        this.final_amount = final_amount;
        this.saved_point = saved_point;
        this.order_id = order_id;
        this.receipt_id = receipt_id;
        this.phone = phone;
        this.orderItems = orderItems;
    }
}