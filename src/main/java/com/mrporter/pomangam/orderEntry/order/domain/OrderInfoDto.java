package com.mrporter.pomangam.orderEntry.order.domain;

import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderInfoItemDto;
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

    private Integer box_no;     // bn

    private String delivery_site_name;  // 배달 장소

    private String detail_site_name;    // 배달 장소 상세

    private Timestamp register_date;    // 주문 일시

    private Byte type_payment;  // 결제 수단 (카드결제 : 1 / 가상계좌 : 2 / 핸드폰결제 : 3 / 카카오 페이 : 4)

    private Byte state_order;   // 주문 상태 (StateOrder 변환)

    private Date arrival_date_only; // 배달 시간

    private Time arrival_time_only; // 배달 시간

    private Integer using_point;    // 사용 포인트

    private Integer using_coupon_name;   // 사용 쿠폰 이름

    private Integer using_coupon_amount;   // 사용 쿠폰 가격

    private Integer final_amount;   // 최종 총 가격

    private String merchant_uid;    // 구매자 식별 아이디

    private String imp_uid; // 주문 인덱스

    List<OrderInfoItemDto> orderItems;  // 주문 제품 목록

}