package com.mrporter.pomangam.client.domains.user.point.rank;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "point_rank_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PointRank extends EntityAuditing {

    /**
     * 계급 레벨
     */
    @Column(name = "level", nullable = false)
    private Short level;

    /**
     * 계급 타이틀
     */
    @Column(name = "title", nullable = false, length = 20)
    private String title;

    /**
     * 등업 조건 - 최소 누적 주문 횟수
     */
    @Column(name = "lower_limit_order_count", nullable = false)
    private Integer lowerLimitOrderCount;

    /**
     * 등업 조건 - 최소 누적 추천인 수
     */
    @Column(name = "lower_limit_recommend_count", nullable = false)
    private Integer lowerLimitRecommendCount;

    /**
     * 등업 혜택 - 제공 쿠폰가격
     */
    @Column(name = "reward_coupon_price", nullable = false)
    private Integer rewardCouponPrice;

    /**
     * 혜택 - 포인트 적립률
     */
    @Column(name = "percent_save_point", nullable = false)
    private Integer percentSavePoint;

    /**
     * 혜택 - 포인트 적립 금액
     */
    @Column(name = "price_save_point", nullable = false)
    private Integer priceSavePoint;

    @Builder
    public PointRank(Long idx, Short level, String title, Integer lowerLimitOrderCount, Integer lowerLimitRecommendCount, Integer rewardCouponPrice, Integer percentSavePoint, Integer priceSavePoint) {
        super.setIdx(idx);
        this.level = level;
        this.title = title;
        this.lowerLimitOrderCount = lowerLimitOrderCount;
        this.lowerLimitRecommendCount = lowerLimitRecommendCount;
        this.rewardCouponPrice = rewardCouponPrice;
        this.percentSavePoint = percentSavePoint;
        this.priceSavePoint = priceSavePoint;
    }
}
