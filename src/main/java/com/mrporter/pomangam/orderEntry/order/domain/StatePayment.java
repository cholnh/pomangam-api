package com.mrporter.pomangam.orderEntry.order.domain;

/**
 * StatePayment
 * 결제 수단 (카드결제 : 1 / 가상계좌 : 2 / 핸드폰결제 : 3 / 카카오 페이 : 4)
 */
public enum StatePayment {

    CARD(1, "신용카드"),
    VBANK(2, "가상계좌"),
    PHONE(3, "핸드폰결제"),
    KAKAOPAY(4, "카카오페이");

    private Byte code;
    private String korName;

    StatePayment(Integer code, String korName) {
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
        for(StatePayment st :  StatePayment.values()) {
            if(st.getCode().compareTo(code) == 0) {
                return st.getKorName();
            }
        }
        return "UNKNOWN";
    }

}
