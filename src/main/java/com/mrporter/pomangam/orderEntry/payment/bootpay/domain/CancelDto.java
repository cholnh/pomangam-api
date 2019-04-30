package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response.ResDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CancelDto extends ResDefault {
    String receipt_id;
    Integer request_cancel_price;
    Integer remain_price;
}
