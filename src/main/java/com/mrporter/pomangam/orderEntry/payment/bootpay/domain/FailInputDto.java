package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FailInputDto {
    int status;
    int code;
    String message;
    String orderId;
}
