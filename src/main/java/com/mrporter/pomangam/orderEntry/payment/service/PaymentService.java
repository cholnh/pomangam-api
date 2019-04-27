package com.mrporter.pomangam.orderEntry.payment.service;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentInputDto;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentResultDto;

public interface PaymentService {
    Order prepare(PaymentInputDto dto);
    Order complete(PaymentResultDto pdto);
}
