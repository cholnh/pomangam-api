package com.mrporter.pomangam.client.domains.deliverysite;

public enum DeliveryType {
    /**
     * 묶음 배달 (기존 포만감 방식)
     */
    BUNDLE,

    /**
     * 개별 배달 (주문 -> 업체 -> 조리 -> 배달 -> 고객)
     */
    INDIVIDUAL,

    /**
     * 정기 배달
     */
    PERIODIC
}
