package com.mrporter.pomangam._bases.utils.bootpay.model.response.verify;

import lombok.Data;

@Data
public class PaymentData {
    String card_name;
    String card_no;
    String card_quota;
    String card_auth_no;
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
    Integer s;
    Integer g;
}
