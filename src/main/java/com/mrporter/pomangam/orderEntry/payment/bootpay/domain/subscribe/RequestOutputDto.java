package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response.ResDefault;
import lombok.Data;

@Data
public class RequestOutputDto extends ResDefault {
    RequestOutputData data;
}

@Data
class RequestOutputData {
    String billing_key;
    String pg_name;
    String pg;
    String method_name;
    String method;
    CardInfo data;
    String e_at;
    String c_at;
}

@Data
class CardInfo {
    String card_code;
    String card_name;
    String card_no;
    String card_cl;
}

