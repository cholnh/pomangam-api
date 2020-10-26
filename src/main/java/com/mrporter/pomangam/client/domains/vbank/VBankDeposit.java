package com.mrporter.pomangam.client.domains.vbank;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vbank_deposit_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VBankDeposit {

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
     * 입금은행명
     */
    @Column(name = "bank", nullable = false, length = 20)
    private String bank;

    /**
     * 잔액
     */
    @Column(name = "remain", nullable = false, columnDefinition = "INT default 0")
    private Integer remain;

    /**
     * 입금자명
     */
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    /**
     * 거래 날짜
     */
    @Column(name = "transfer_date", nullable = false, length = 25)
    private String transferDate;

    /**
     * 거래내용
     */
    @Column(name = "content", nullable = false, length = 30)
    private String content;

    /**
     * 거래 상태
     */
    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private VBankStatus status;

    /**
     * 주문 인덱스
     */
    @Column(name = "idx_order", nullable = true)
    private Long idxOrder;

    @Builder
    public VBankDeposit(LocalDateTime registerDate, Integer input, String bank, Integer remain, String name, String transferDate, String content, VBankStatus status, Long idxOrder) {
        this.registerDate = registerDate;
        this.input = input;
        this.bank = bank;
        this.remain = remain;
        this.name = name;
        this.transferDate = transferDate;
        this.content = content;
        this.status = status;
        this.idxOrder = idxOrder;
    }
}
