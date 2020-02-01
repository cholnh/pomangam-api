package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe;

import lombok.Data;

import java.util.List;

@Data
public class SubscribeInputDto {
    String billing_key;
    String item_name;
    String order_id;
    Integer price;
    List items;
}
