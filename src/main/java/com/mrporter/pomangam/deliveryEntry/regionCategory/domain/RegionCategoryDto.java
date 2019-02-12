package com.mrporter.pomangam.deliveryEntry.regionCategory.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class RegionCategoryDto implements Serializable {

    private Integer idx;

    private String name;

    public RegionCategory toEntity() {
        return null;
    }
}