package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDataDto {
    String card_name;
    String card_no;
    String card_quota;
    String card_auth_no;
    String receipt_url;
    String receipt_id;
    String n;
    String p;
    String pg;
    String pg_a;
    String pm_a;
    String o_id;
    String p_at;
    String s;
    String g;
}