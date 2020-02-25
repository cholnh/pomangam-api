package com.mrporter.pomangam.client.domains.payment;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "payment_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Payment extends EntityAuditing {

    /**
     * 결제 수단 종류
     */
    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Builder
    public Payment(Long idx, PaymentType paymentType) {
        super.setIdx(idx);
        this.paymentType = paymentType;
    }
}

