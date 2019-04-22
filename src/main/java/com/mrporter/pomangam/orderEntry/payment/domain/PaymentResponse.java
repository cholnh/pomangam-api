package com.mrporter.pomangam.orderEntry.payment.domain;

import lombok.Data;

@Data
public class PaymentResponse {
    Integer code;
    String message;
    PaymentAnnotation response;
}
