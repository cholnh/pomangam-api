package com.mrporter.pomangam.deliveryEntry.deliverySite.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class DeliverySiteDto implements Serializable {

    private Integer idx;

    private String name;

    private String location;

    private Integer regionCategoryIdx;

    private String campus;

    public DeliverySiteDto(Integer idx, String name, String location, Integer regionCategoryIdx, String campus) {
        this.idx = idx;
        this.name = name;
        this.location = location;
        this.regionCategoryIdx = regionCategoryIdx;
        this.campus = campus;
    }

    public DeliverySite toEntity() {
        return null;
    }
}