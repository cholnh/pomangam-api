package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe;

import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.enums.PG;
import lombok.Data;

@Data
public class RequestInputDto {
    String pg;
    String card_no;
    String card_pw;
    String expire_year;
    String expire_month;
    String identify_number;
    String item_name;
    String order_id;
}
