package com.mrporter.pomangam._bases.utils.bootpay.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cancel {
    public String receipt_id;
    public String name;
    public String reason;
    public Double price;

    @Builder
    public Cancel(String receipt_id, String name, String reason, Double price) {
        this.receipt_id = receipt_id;
        this.name = name;
        this.reason = reason;
        this.price = price;
    }
}
