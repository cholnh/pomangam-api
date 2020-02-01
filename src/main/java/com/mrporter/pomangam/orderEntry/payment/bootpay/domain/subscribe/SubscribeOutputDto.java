package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe;

import lombok.Data;

@Data
public class SubscribeOutputDto {
    String receipt_id;
    Integer price;
    String card_no;
    String card_code;
    String card_name;
    String card_quota;
    Object params;
    String item_name;
    String order_id;
    String pg_name;
    String pg;
    String method;
    String method_name;
    String requested_at;
    String purchased_at;
    Integer status;
}
