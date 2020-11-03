package com.mrporter.pomangam.client.domains.vbank;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "vbank_refund_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VBankRefund extends EntityAuditing {

    /**
     * 환불금액
     */
    @Column(name = "refund_price", nullable = false, columnDefinition = "INT default 0")
    private Integer refundPrice;

    /**
     * 입금 은행명
     */
    @Column(name = "client_bank", nullable = true, length = 20)
    private String clientBank;

    /**
     * 입금자명
     */
    @Column(name = "client_name", nullable = true, length = 20)
    private String clientName;

    /**
     * 입금 계좌
     */
    @Column(name = "client_account", nullable = true, length = 50)
    private String clientAccount;

    /**
     * 환불 성공 날짜
     */
    @Column(name = "refund_date", nullable = false, length = 25)
    private String refundDate;

    /**
     * 비고
     */
    @Column(name = "note", nullable = false, length = 255)
    private String note;

    /**
     * 환불 상태
     */
    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private VBankRefundStatus status;

    /**
     * 주문 인덱스
     */
    @Column(name = "idx_order", nullable = true)
    private Long idxOrder;

    @Builder
    public VBankRefund(Integer refundPrice, String clientBank, String clientName, String clientAccount, String refundDate, String note, VBankRefundStatus status, Long idxOrder) {
        this.refundPrice = refundPrice;
        this.clientBank = clientBank;
        this.clientName = clientName;
        this.clientAccount = clientAccount;
        this.refundDate = refundDate;
        this.note = note;
        this.status = status;
        this.idxOrder = idxOrder;
    }
}
