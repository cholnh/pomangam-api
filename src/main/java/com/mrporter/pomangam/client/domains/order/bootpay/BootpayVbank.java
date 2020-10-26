package com.mrporter.pomangam.client.domains.order.bootpay;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bootpay_vbank_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BootpayVbank extends EntityAuditing {

    /**
     * order index
     */
    @Column(name = "idx_order", nullable = false)
    private Long idxOrder;

    /**
     * 가상계좌 이름
     * 글자수: utf8 기준 / 영문 40자 / 한글 40자
     */
    @Column(name = "vbank_name", nullable = false, length = 40)
    private String vbankName;

    /**
     * 가상계좌 계좌
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name = "vbank_account", nullable = false, length = 100)
    private String vbankAccount;

    /**
     * 가상계좌 금액
     * 글자수: utf8 기준 / 영문 40자 / 한글 40자
     */
    @Column(name = "vbank_price", nullable = false)
    private Integer vbankPrice;

    @Builder
    public BootpayVbank(Long idxOrder, String vbankName, String vbankAccount, Integer vbankPrice) {
        this.idxOrder = idxOrder;
        this.vbankName = vbankName;
        this.vbankAccount = vbankAccount;
        this.vbankPrice = vbankPrice;
    }
}
