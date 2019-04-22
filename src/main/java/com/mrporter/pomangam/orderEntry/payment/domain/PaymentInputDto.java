package com.mrporter.pomangam.orderEntry.payment.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PaymentInputDto implements Serializable {
    private Integer cartIdx;

    private Integer usingPoint;

    private String usingCouponCode;

    private Byte typePayment;

    public PaymentInputDto(Integer cartIdx, Integer usingPoint, String usingCouponCode, Byte typePayment) {
        this.cartIdx = cartIdx;
        this.usingPoint = usingPoint;
        this.usingCouponCode = usingCouponCode;
        this.typePayment = typePayment;
    }
}
