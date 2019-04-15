package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CountSearchDeliverySiteDto implements Serializable {

    private Integer idx;

    private Integer deliverySiteIdx;

    private long count;

    public CountSearchDeliverySiteDto(Integer idx, Integer deliverySiteIdx, BigInteger count) {
        this.idx = idx;
        this.deliverySiteIdx = deliverySiteIdx;
        this.count = count==null?0:count.longValue();
    }
}