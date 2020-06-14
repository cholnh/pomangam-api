package com.mrporter.pomangam.client.domains.order.cash_receipt;

public enum CashReceiptType {

    /**
     * 개인소득공제 (휴대폰번호)
     */
    PERSONAL_PHONE_NUMBER,

    /**
     * 개인소득공제 (현금영수증카드)
     */
    PERSONAL_CARD_NUMBER,

    /**
     * 사업자증빙 (사업자등록번호)
     */
    BUSINESS_REGISTRATION_NUMBER,

    /**
     * 사업자증빙 (현금영수증카드)
     */
    BUSINESS_CARD_NUMBER
}
