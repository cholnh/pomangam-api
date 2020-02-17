package com.mrporter.pomangam.client.domains.product.cost;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

@Data
@NoArgsConstructor
@Embeddable
public class Cost {

    /**
     * 제품 원가
     */
    @Column(name = "unit_cost", nullable = false)
    private Integer unitCost;

    /**
     * 업체 판매 수수료 (가격)
     */
    @Column(name = "price_store_fee", nullable = false)
    private Integer priceStoreFee;

    /**
     * 업체 판매 수수료 (퍼센트)
     */
    @Column(name = "percent_store_fee", nullable = false)
    private Integer percentStoreFee;

    /**
     * 고객 판매 수수료 (가격)
     */
    @Column(name = "price_client_fee", nullable = false)
    private Integer priceClientFee;

    /**
     * 고객 판매 수수료 (퍼센트)
     */
    @Column(name = "percent_client_fee", nullable = false)
    private Integer percentClientFee;

    @PrePersist
    private void prePersist() {
        this.unitCost = unitCost == null ? 0 : unitCost;
        this.priceStoreFee = priceStoreFee == null ? 0 : priceStoreFee;
        this.percentStoreFee = percentStoreFee == null ? 0 : percentStoreFee;
        this.priceClientFee = priceClientFee == null ? 0 : priceClientFee;
        this.percentClientFee = percentClientFee == null ? 0 : percentClientFee;
    }

    /**
     * 판매가 = 원가 + 고객 수수료 총합
     * @return 판매 가격
     */
    public int getSalePrice() {
        return this.unitCost + this.priceClientFee + (this.unitCost*this.percentClientFee/100);
    }

    /**
     * 업체 수수료 총합
     * @return 업체 수수료 총합
     */
    public int getTotalStoreFee() {
        return this.priceStoreFee + (this.unitCost*this.percentStoreFee/100);
    }

    @Builder
    public Cost(Integer unitCost, Integer priceStoreFee, Integer percentStoreFee, Integer priceClientFee, Integer percentClientFee) {
        this.unitCost = unitCost;
        this.priceStoreFee = priceStoreFee;
        this.percentStoreFee = percentStoreFee;
        this.priceClientFee = priceClientFee;
        this.percentClientFee = percentClientFee;
    }
}
