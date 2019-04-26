package com.mrporter.pomangam.orderEntry.order.domain;

public enum StateOrder {
    ORDER_READY(0, "주문대기"),
    ORDER_SUCCESS(1, "주문성공"),
    ORDER_FAIL(2, "주문실패"),
    ORDER_DELIVERED(3, "배달완료"),
    ORDER_CANCEL(4, "취소"),
    ORDER_REFUND(5, "환불");

    private Byte code;
    private String korName;

    StateOrder(Integer code, String korName) {
        this.code = Byte.valueOf(code.toString());
        this.korName = korName;
    }

    public Byte getCode() {
        return code;
    }

    public String getKorName() {
        return korName;
    }

    public static String toString(Byte code) {
        for(StateOrder st :  StateOrder.values()) {
            if(st.getCode().compareTo(code) == 0) {
                return st.getKorName();
            }
        }
        return "UNKNOWN";
    }
}
