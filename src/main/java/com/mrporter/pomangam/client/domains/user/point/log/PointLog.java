package com.mrporter.pomangam.client.domains.user.point.log;

import com.mrporter.pomangam.client.domains.order.OrderType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "point_log_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PointLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    /**
     * 고객 인덱스
     */
    @Column(name = "idx_user", nullable = false)
    private Long idxUser;

    /**
     * 포인트 타입
     */
    @Column(name = "point_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PointType pointType;

    /**
     * 변경 포인트
     */
    @Column(name = "point", nullable = false)
    private Integer point;

    /**
     * 변경 후 포인트
     */
    @Column(name = "post_point", nullable = false)
    private Integer postPoint;

    /**
     * 등록 날짜
     */
    @Column(name = "register_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime registerDate;

    @Builder
    public PointLog(Long idxUser, PointType pointType, Integer point, Integer postPoint, LocalDateTime registerDate) {
        this.idxUser = idxUser;
        this.pointType = pointType;
        this.point = point;
        this.postPoint = postPoint;
        this.registerDate = registerDate;
    }
}
