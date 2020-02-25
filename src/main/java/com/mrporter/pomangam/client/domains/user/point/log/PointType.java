package com.mrporter.pomangam.client.domains.user.point.log;

public enum PointType {

    /**
     * 관리자에 의한 포인트 발급
     */
    ISSUED_BY_ADMIN,

    /**
     * 프로모션에 의한 포인트 발급
     */
    ISSUED_BY_PROMOTION,

    /**
     * 구매활동에 의한 포인트 발급
     */
    ISSUED_BY_BUY,

    /**
     * 주문취소에 의한 포인트 환급
     */
    ROLLBACK_BY_PAYMENT_CANCEL,

    /**
     * 구매활동에 의한 포인트 사용
     */
    USED_BY_BUY,

    /**
     * 관리자에 의한 포인트 수정
     */
    UPDATED_BY_ADMIN
}

