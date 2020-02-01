package com.mrporter.pomangam.orderEntry.payment.bootpay.service;

import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.*;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.request.Cancel;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe.RequestInputDto;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe.RequestOutputDto;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe.SubscribeInputDto;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.subscribe.SubscribeOutputDto;

public interface PaymentService {
    PaymentOutputDto prepare(PaymentInputDto dto);
    OrderInfoDto complete(CompleteInputDto dto);
    CancelDto cancel(Cancel cancel);
    void fail(FailInputDto dto);
    String goPayLink(PaymentLinkInputDto paymentLinkInputDto);
    RequestOutputDto goRequestBilling(RequestInputDto requestInputDto);
    SubscribeOutputDto goSubscribeBilling(SubscribeInputDto subscribeInputDto);
}
