package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response.ResDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VerifyDto extends ResDefault {
    VerifyDataDto data;
}
