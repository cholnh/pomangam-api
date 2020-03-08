package com.mrporter.pomangam.test.data.payment;

import com.mrporter.pomangam.client.domains.payment.Payment;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.repositories.payment.PaymentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PaymentData {

    @Autowired
    PaymentJpaRepository paymentJpaRepository;

    @Transactional
    public void of(Long idx, PaymentType type) {
        Payment payment = Payment.builder()
                .idx(idx)
                .paymentType(PaymentType.CREDIT_CARD)
                .build();
        paymentJpaRepository.save(payment);
    }
}