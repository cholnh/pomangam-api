package com.mrporter.pomangam.client.domains.order.log;

import com.mrporter.pomangam.client.domains.order.OrderType;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    /**
     * 주문 인덱스
     */
    @Column(name = "idx_order", nullable = false)
    private Long idxOrder;

    /**
     * 주문 타입
     */
    @Column(name = "order_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    /**
     * 등록 날짜
     */
    @Column(name = "register_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime registerDate;

    @Builder
    public OrderLog(Long idxOrder, OrderType orderType, LocalDateTime registerDate) {
        this.idxOrder = idxOrder;
        this.orderType = orderType;
        this.registerDate = registerDate;
    }
}
