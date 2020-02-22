package com.mrporter.pomangam.client.domains.coupon;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupon_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Coupon extends EntityAuditing {

    /**
     * 쿠폰 할인가
     */
    @Column(name = "discount_cost", nullable = false)
    private Integer discountCost;

    /**
     * 쿠폰 제목
     */
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /**
     * 쿠폰 식별 코드
     */
    @Column(name = "code", nullable = false, length = 100)
    private String code;

    /**
     * 등록 고객
     * 양방향 매핑
     */
    @JoinColumn(name = "idx_user")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User user;

    /**
     * 쿠폰 적용 시작 기간
     */
    @Column(name = "begin_date", nullable = false)
    private LocalDateTime beginDate;

    /**
     * 쿠폰 적용 종료 기간
     * null: 기한 무제한
     */
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    public boolean isValid() {
        return super.getIsActive() &&
                LocalDateTime.now().isAfter(this.beginDate) &&
                (this.endDate == null || LocalDateTime.now().isBefore(this.endDate));
    }

    @Builder
    public Coupon(Integer discountCost, String title, String code, User user, LocalDateTime beginDate, LocalDateTime endDate) {
        this.discountCost = discountCost;
        this.title = title;
        this.code = code;
        this.user = user;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}

