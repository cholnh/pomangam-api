package com.mrporter.pomangam.orderEntry.payment.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PaymentResultDto implements Serializable {
    private String imp_uid;
    private String merchant_uid;

    public PaymentResultDto(String imp_uid, String merchant_uid) {
        this.imp_uid = imp_uid;
        this.merchant_uid = merchant_uid;
    }
}
