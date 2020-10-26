package com.mrporter.pomangam._bases.utils.bootpay.model.response.cancel;

import lombok.Data;

@Data
public class CancelData {
    String receipt_id;
    Integer request_cancel_price;
    Integer remain_price;
    Integer remain_tax_free;
    Integer cancelled_price;
    Integer cancelled_tax_free;
    String revoked_at;
    String tid;
}
