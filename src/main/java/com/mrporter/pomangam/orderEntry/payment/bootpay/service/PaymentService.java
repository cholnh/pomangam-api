package com.mrporter.pomangam.orderEntry.payment.bootpay.service;

import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.*;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.request.Cancel;

public interface PaymentService {
    PaymentOutputDto prepare(PaymentInputDto dto);
    OrderInfoDto complete(CompleteInputDto dto);
    CancelDto cancel(Cancel cancel);
    void fail(FailInputDto dto);
}
