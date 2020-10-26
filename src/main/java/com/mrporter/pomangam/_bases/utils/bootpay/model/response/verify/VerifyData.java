package com.mrporter.pomangam._bases.utils.bootpay.model.response.verify;

import lombok.Data;

@Data
public class VerifyData {
    String receipt_id;
    String order_id;
    String name;
    Integer price;
    Integer tax_free;
    Integer remain_price;
    Integer remain_tax_free;
    Integer cancelled_price;
    Integer cancelled_tax_free;
    String receipt_url;
    String unit;
    String pg;
    String method;
    String pg_name;
    String method_name;
    PaymentData payment_data;
    String requested_at;
    String purchased_at;
    Integer status;
    String status_en;
    String status_ko;
}
