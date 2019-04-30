package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Created by ehowlsla on 2017. 8. 3..
 */

@Builder
@AllArgsConstructor
public class Cancel {
    public String receipt_id;
    public String name;
    public String reason;
}
