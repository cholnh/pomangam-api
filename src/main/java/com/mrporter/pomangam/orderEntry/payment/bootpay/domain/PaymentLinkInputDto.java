package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import lombok.Data;

import java.util.List;

@Data
public class PaymentLinkInputDto {
    Integer price;
    Integer tax_free;
    String application_id;
    String name;
    String pg;
    String method;
    Integer show_agree_window;
    List<String> items;
    _User user_info;
    String order_id;
    Object params;
    String account_expire_at;
    String expire_month;
    Integer vbank_result;
    List quota;
    Object extra;
}

@Data
class _User {
    String username;
    String email;
    String addr;
    String phone;
}
