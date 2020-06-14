package com.mrporter.pomangam.client.domains.order.payment_info;

import com.mrporter.pomangam.client.domains.order.cash_receipt.CashReceiptType;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import com.mrporter.pomangam.client.domains.promotion.Promotion;
import com.mrporter.pomangam.client.domains.promotion.PromotionMapper;
import com.mrporter.pomangam.client.domains.user.coupon.CouponMapper;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
@ToString(exclude = {"usingCoupons", "usingPromotions"})
public class PaymentInfo {

    /**
     * 결제 수단 타입
     */
    @Column(name = "payment_type", nullable = true, length = 30)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

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

    /**
     * 현금영수증 종류
     */
    @Column(name = "cash_receipt_type", nullable = true, length = 30)
    @Enumerated(EnumType.STRING)
    private CashReceiptType cashReceiptType;

    public boolean hasCashReceipt() {
        return cashReceipt != null && !cashReceipt.isEmpty();
    }

    public int discountCost() {
        int discountTotalCost = 0;
        // int promotionTotalCost = 0;

        // 포인트 할인 적용
        discountTotalCost += usingPoint == null ? 0 : usingPoint;

        // 프로모션 할인 적용
        if(usingPromotions != null) {
            for(PromotionMapper promotionMapper : usingPromotions) {
                Promotion promotion = promotionMapper.getPromotion();
                if(promotion.isValid()) {
                    int promotionCost = promotion.getDiscountCost();
                    discountTotalCost += promotionCost;
                    // promotionTotalCost += promotionCost;
                }
            }
        }

        // 쿠폰 할인 적용
        if(usingCoupons != null) {
            for(CouponMapper couponMapper : usingCoupons) {
                Coupon coupon = couponMapper.getCoupon();
                discountTotalCost += coupon.getDiscountCost();
            }
        }
        return discountTotalCost;
    }

    public void usingCouponsClear() {
        this.usingCoupons.clear();
    }

    @Builder
    public PaymentInfo(PaymentType paymentType, Integer usingPoint, List<CouponMapper> usingCoupons, List<PromotionMapper> usingPromotions, Integer savedPoint, String cashReceipt, CashReceiptType cashReceiptType) {
        this.paymentType = paymentType;
        this.usingPoint = usingPoint;
        this.usingCoupons = usingCoupons;
        this.usingPromotions = usingPromotions;
        this.savedPoint = savedPoint;
        this.cashReceipt = cashReceipt;
        this.cashReceiptType = cashReceiptType;
    }
}
