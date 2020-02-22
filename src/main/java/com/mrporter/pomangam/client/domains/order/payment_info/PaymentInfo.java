package com.mrporter.pomangam.client.domains.order.payment_info;

import com.mrporter.pomangam.client.domains.coupon.Coupon;
import com.mrporter.pomangam.client.domains.promotion.Promotion;
import com.mrporter.pomangam.client.domains.promotion.PromotionMapper;
import com.mrporter.pomangam.client.domains.coupon.CouponMapper;
import com.mrporter.pomangam.client.domains.payment.Payment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class PaymentInfo {

    /**
     * 결제 수단
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_payment")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Payment payment;

    /**
     * 사용 포인트
     */
    @Column(name = "using_point", nullable = false, columnDefinition = "INT default 0")
    private Integer usingPoint;

    /**
     * 사용 쿠폰
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<CouponMapper> usingCoupons = new ArrayList<>();

    /**
     * 적용된 프로모션
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<PromotionMapper> usingPromotions = new ArrayList<>();

    /**
     * 적립 포인트
     */
    @Column(name = "saved_point", nullable = false, columnDefinition = "INT default 0")
    private Integer savedPoint;

    /**
     * 현금영수증 번호
     */
    @Column(name = "cash_receipt", nullable = true, length = 100)
    private String cashReceipt;

    public boolean hasCashReceipt() {
        return cashReceipt != null && !cashReceipt.isEmpty();
    }

    public int discountCost() {
        int discountTotalCost = 0;

        // 포인트 할인 적용
        discountTotalCost += usingPoint == null ? 0 : usingPoint.intValue();

        // 쿠폰 할인 적용
        if(usingCoupons != null) {
            for(CouponMapper couponMapper : usingCoupons) {
                Coupon coupon = couponMapper.getCoupon();
                if(coupon.isValid()) {
                    discountTotalCost += coupon.getDiscountCost().intValue();
                }
            }
        }

        // 프로모션 할인 적용
        if(usingPromotions != null) {
            for(PromotionMapper promotionMapper : usingPromotions) {
                Promotion promotion = promotionMapper.getPromotion();
                if(promotion.isValid()) {
                    discountTotalCost += promotion.getDiscountCost().intValue();
                }
            }
        }
        return discountTotalCost;
    }

    @Builder
    public PaymentInfo(Payment payment, Integer usingPoint, List<CouponMapper> usingCoupons, List<PromotionMapper> usingPromotions, Integer savedPoint, String cashReceipt) {
        this.payment = payment;
        this.usingPoint = usingPoint;
        this.usingCoupons = usingCoupons;
        this.usingPromotions = usingPromotions;
        this.savedPoint = savedPoint;
        this.cashReceipt = cashReceipt;
    }
}
