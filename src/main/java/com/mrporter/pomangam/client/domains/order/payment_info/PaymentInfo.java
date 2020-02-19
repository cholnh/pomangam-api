package com.mrporter.pomangam.client.domains.order.payment_info;

import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import com.mrporter.pomangam.client.domains.user.payment.Payment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class PaymentInfo {

    /**
     * 결제 수단
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_payment")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Payment payment;

    /**
     * 최종 총 가격
     * 포인트, 쿠폰 등을 적용 한 최종 가격 (결제한 금액)
     */
    @Column(name = "total_cost", nullable = false)
    private Integer totalCost;

    /**
     * 사용 포인트
     */
    @Column(name = "using_point", nullable = false, columnDefinition = "INT default 0")
    private Integer usingPoint;

    /**
     * 사용 쿠폰
     */
    @Column(name = "using_coupon", nullable = true)
    private Coupon usingCoupon;

    /**
     * 적립 포인트
     */
    @Column(name = "saved_point", nullable = false, columnDefinition = "INT default 0")
    private Integer savedPoint;
}
