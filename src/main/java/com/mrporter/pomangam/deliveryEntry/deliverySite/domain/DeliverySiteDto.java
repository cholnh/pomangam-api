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

    private Integer region_category_idx;

    public DeliverySite toEntity() {
        return null;
    }
}