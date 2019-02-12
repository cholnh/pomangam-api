package com.mrporter.pomangam.productEntry.product.domain;

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
    private Integer idx;

    private Integer store_idx;

    private String name;

    private String description;

    private String sub_description;

    private Integer category_id;

    private String category_name;

    private Byte state_active;

    private Byte type;

    private Integer sequence;

    private Integer like_count;

    @Builder
    public Product(Integer store_idx, String name, String description, String sub_description, Integer category_id, String category_name, Byte state_active, Byte type, Integer sequence, Integer like_count) {
        this.store_idx = store_idx;
        this.name = name;
        this.description = description;
        this.sub_description = sub_description;
        this.category_id = category_id;
        this.category_name = category_name;
        this.state_active = state_active;
        this.type = type;
        this.sequence = sequence;
        this.like_count = like_count;
    }
}
