package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response.ResDefault;
import lombok.Data;

@Data
public class VerifyDto extends ResDefault {
    VerifyDataDto data;
}
