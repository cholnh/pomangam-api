package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response.VerifyPaymentDataDto;
import lombok.Data;

@Data
public class VerifyDataDto {
    String receipt_id;
    String order_id;
    String name;
    Integer price;
    String receipt_url;
    String unit;
    String pg;
    String method;
    String pg_name;
    String method_name;
    VerifyPaymentDataDto payment_data;
    String requested_at;
    String purchased_at;
    Integer status;
    String status_en;
    String status_ko;
}
