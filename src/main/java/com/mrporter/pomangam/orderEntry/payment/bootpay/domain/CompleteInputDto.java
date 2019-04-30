package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteInputDto {
    String receiptId;
    String orderId;
}
