package com.mrporter.pomangam.client.domains.order;

public enum OrderType {
    /**
     * 결제 준비
     * (가상계좌, 무통장입금 등)
     */
    PAYMENT_READY,

    /**
     * 결제 취소
     * 결제 준비 상태에서 결제를 진행하지 않고 취소한 경우.
     */
    PAYMENT_CANCEL,

    /**
     * 결제 실패
     */
    PAYMENT_FAIL,

    /**
     * 주문 성공
     */
    ORDER_SUCCESS,

    /**
     * 주문 취소
     * 1. 업체가 주문을 수락하지 않은 상태에서 사용자가 취소한 경우 (단순 pg 환불, 취소)
     * 2. 업체측에서 자체적으로 취소한 경우 (고객의 요청, 업체 바쁨 등)
     * 3. 관리자측 취소
     */
    ORDER_CANCEL,

    /**
     * 주문 환불
     * STORE: 업체측 잘못으로 인한 환불 (주문실수, 누락 등)
     * ADMIN: 플랫폼측 잘못으로 인한 환불 (서버오류, 배달기사의 실수, 누락 등)
     */
    ORDER_REFUND_STORE,
    ORDER_REFUND_ADMIN,

    /**
     * 주문 실패
     * 1. 서버 내 오류 (주문 로직)
     * 2. 사용자의 악의적 값 입력
     */
    ORDER_FAIL,

    /**
     * 배달 성공
     */
    DELIVERY_SUCCESS,


}
