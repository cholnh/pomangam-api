package com.mrporter.pomangam.deliveryEntry.regionCategory.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "region_category_tbl;")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class RegionCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String name;

    @Builder
    public RegionCategory(String name) {
        this.name = name;
    }
}
