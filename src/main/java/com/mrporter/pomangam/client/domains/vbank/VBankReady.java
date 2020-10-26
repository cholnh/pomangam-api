package com.mrporter.pomangam.client.domains.vbank;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vbank_ready_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VBankReady {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    /**
     * 등록 날짜
     */
    @Column(name = "register_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime registerDate;

    /**
     * 입금액
     */
    @Column(name = "input", nullable = false, columnDefinition = "INT default 0")
    private Integer input;

    /**
     * 입금자명
     */
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    /**
     * 주문 인덱스
     */
    @Column(name = "idx_order", nullable = false)
    private Long idxOrder;

    @Builder
    public VBankReady(LocalDateTime registerDate, Integer input, String name, Long idxOrder) {
        this.registerDate = registerDate;
        this.input = input;
        this.name = name;
        this.idxOrder = idxOrder;
    }
}
