package com.mrporter.pomangam.deliveryEntry.deliverySite.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class DeliverySiteWithCountDto implements Serializable {

    private Integer idx;

    private String name;

    private String location;

    private Integer regionCategoryIdx;

    private String campus;

    private long count;

    public DeliverySiteWithCountDto(Integer idx, String name, String location, Integer regionCategoryIdx, String campus, BigInteger count) {
        this.idx = idx;
        this.name = name;
        this.location = location;
        this.regionCategoryIdx = regionCategoryIdx;
        this.campus = campus;
        this.count = count==null?0:count.longValue();
    }

    public DeliverySite toEntity() {
        return null;
    }
}