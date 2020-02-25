package com.mrporter.pomangam.client.domains.ordertime;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "order_time_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderTime extends EntityAuditing {

    /**
     * 배달 도착 시간
     */
    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    /**
     * 음식 픽업 시간
     */
    @Column(name = "pick_up_time", nullable = false)
    private LocalTime pickUpTime;

    /**
     * 주문 종료 시간
     */
    @Column(name = "order_end_time", nullable = false)
    private LocalTime orderEndTime;

    @Builder
    public OrderTime(Long idx, LocalTime arrivalTime, LocalTime pickUpTime, LocalTime orderEndTime) {
        super.setIdx(idx);
        this.arrivalTime = arrivalTime;
        this.pickUpTime = pickUpTime;
        this.orderEndTime = orderEndTime;
    }
}

