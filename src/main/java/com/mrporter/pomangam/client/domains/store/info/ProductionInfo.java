package com.mrporter.pomangam.client.domains.store.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class ProductionInfo {

    /**
     * 최소 생산 가능 시간
     * (단위: 분)
     */
    @Column(name = "minimum_time", nullable = false)
    private Short minimumTime;

    /**
     * 평균 병렬 생산량
     * (단위: 수량/1분)
     */
    @Column(name = "parallel_production", nullable = false)
    private Short parallelProduction;

    /**
     * 최대 생성 가능 수량
     */
    @Column(name = "maximumProduction", nullable = false)
    private Short maximumProduction;

    @PrePersist
    private void prePersist() {
        this.minimumTime = minimumTime != null && minimumTime.intValue() <= 0 ? 1 : minimumTime;
    }

    @Builder
    public ProductionInfo(Short minimumTime, Short parallelProduction, Short maximumProduction) {
        this.minimumTime = minimumTime;
        this.parallelProduction = parallelProduction;
        this.maximumProduction = maximumProduction;
    }
}
