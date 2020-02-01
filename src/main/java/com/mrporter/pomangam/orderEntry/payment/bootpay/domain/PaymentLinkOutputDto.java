package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response.ResDefault;
import lombok.Data;

@Data
public class PaymentLinkOutputDto extends ResDefault {
    String data;
}
