package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response;

import lombok.Data;

@Data
public class VerifyPaymentDataDto {
    String receipt_id;
    String n;
    Integer p;
    String tid;
    String pg;
    String pm;
    String pg_a;
    String pm_a;
    String o_id;
    String p_at;
    String r_at;
    Integer s;
    Integer g;
}
