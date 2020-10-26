package com.mrporter.pomangam.client.domains.user.point.log;

public enum PointType {

    /**
     * 프로모션에 의한 포인트 발급
     */
    ISSUED_BY_PROMOTION,

    /**
     * 구매활동에 의한 포인트 발급
     */
    ISSUED_BY_BUY,

    /**
     * 주문취소에 의한 사용 포인트 재발급
     */
    ROLLBACK_ISSUED_BY_PAYMENT_CANCEL,

    /**
     * 주문취소에 의한 적립 포인트 회수
     */
    ROLLBACK_SAVED_BY_PAYMENT_CANCEL,

    /**
     * 구매활동에 의한 포인트 사용
     */
    USED_BY_BUY,

    /**
     * 관리자에 의한 포인트 수정 (증가)
     */
    UPDATED_PLUS_BY_ADMIN,

    /**
     * 관리자에 의한 포인트 수정 (차감)
     */
    UPDATED_MINUS_BY_ADMIN
}

