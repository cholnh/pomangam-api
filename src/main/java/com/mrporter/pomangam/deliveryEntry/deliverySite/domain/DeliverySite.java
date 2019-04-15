package com.mrporter.pomangam.deliveryEntry.deliverySite.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "delivery_site_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class DeliverySite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String name;

    private String location;

    @Column(name = "region_category_idx")
    private Integer regionCategoryIdx;

    private String campus;

    @Builder
    public DeliverySite(String name, String location, Integer regionCategoryIdx, String campus) {
        this.name = name;
        this.location = location;
        this.regionCategoryIdx = regionCategoryIdx;
        this.campus = campus;
    }
}
