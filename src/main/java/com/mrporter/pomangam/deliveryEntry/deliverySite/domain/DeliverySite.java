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

    private Integer region_category_idx;

    @Builder
    public DeliverySite(String name, String location, Integer region_category_idx) {
        this.name = name;
        this.location = location;
        this.region_category_idx = region_category_idx;
    }
}
