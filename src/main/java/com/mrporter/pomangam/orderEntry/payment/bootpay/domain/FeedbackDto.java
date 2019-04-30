package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    String receipt_id;
    String pg;
    String pg_name;
    String method;
    String method_name;
    String application_id;
    String name;
    String private_key;
    String order_id;
    String params;
    PaymentDataDto payment_data;
    String price;
    String status;
}