package com.mrporter.pomangam.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "product_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long store_idx;

    private String name;

    private String description;

    private String sub_description;

    private Long category_id;

    private String category_name;

    private Integer minimum_time;

    private Integer parallel_production;

    private Integer sales_volume;

    private Integer status;

    private Integer type;

    private Long sequence;

    private Long like_count;

    @Builder
    public Product(Long store_idx, String name, String description, String sub_description, Long category_id, String category_name, Integer minimum_time, Integer parallel_production, Integer sales_volume, Integer status, Integer type, Long sequence, Long like_count) {
        this.store_idx = store_idx;
        this.name = name;
        this.description = description;
        this.sub_description = sub_description;
        this.category_id = category_id;
        this.category_name = category_name;
        this.minimum_time = minimum_time;
        this.parallel_production = parallel_production;
        this.sales_volume = sales_volume;
        this.status = status;
        this.type = type;
        this.sequence = sequence;
        this.like_count = like_count;
    }
}
