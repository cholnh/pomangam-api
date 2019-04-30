package com.mrporter.pomangam.orderEntry.payment.bootpay.domain;

import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderInfoItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOutputDto {
    String customerName;
    String customerId;
    String deliverySiteName;
    String phone;
    String orderId;
    Integer final_amount;
    List<OrderInfoItemDto> orderItems;
}
