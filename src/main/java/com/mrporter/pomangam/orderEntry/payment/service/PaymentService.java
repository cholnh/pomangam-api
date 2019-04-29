package com.mrporter.pomangam.orderEntry.payment.service;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentInputDto;

public interface PaymentService {
    Order prepare(PaymentInputDto dto);
    Order complete(String imp_uid);
}
