package com.mrporter.pomangam.promotionEntry.pointLog.domain;

public enum StatePointLog {
    ISSUE_BY_ADMIN(00),
    ISSUE_BY_PROMOTION(01),
    ISSUE_BY_PAYMENT(02),

    USED_BY_PAYMENT(10),

    EDIT_BY_ADMIN(20);

    private Byte code;

    StatePointLog(Integer code) {
        this.code = Byte.valueOf(code.toString());
    }

    public Byte getCode() {
        return code;
    }

    public static StatePointLog getState(Byte code) {
        StatePointLog state = null;
        switch (code) {
            case 0 :
                state = StatePointLog.ISSUE_BY_ADMIN;
                break;
            case 1 :
                state = StatePointLog.ISSUE_BY_PROMOTION;
                break;
            case 2 :
                state = StatePointLog.ISSUE_BY_PAYMENT;
                break;
            case 10 :
                state = StatePointLog.USED_BY_PAYMENT;
                break;
            case 20 :
                state = StatePointLog.EDIT_BY_ADMIN;
                break;
        }
        return state;
    }

}
