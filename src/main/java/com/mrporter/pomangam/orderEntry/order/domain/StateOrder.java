package com.mrporter.pomangam.orderEntry.order.domain;

public enum StateOrder {
    ORDER_READY(0),
    ORDER_SUCCESS(1),
    ORDER_FAIL(2),
    ORDER_DELIVERED(3),
    ORDER_CANCEL(4),
    ORDER_REFUND(5);

    private Byte code;

    StateOrder(Integer code) {
        this.code = Byte.valueOf(code.toString());
    }

    public Byte getCode() {
        return code;
    }
}
