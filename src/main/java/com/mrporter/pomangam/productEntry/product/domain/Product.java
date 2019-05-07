package com.mrporter.pomangam.productEntry.product.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "product_tbl")
@NoArgsConstructor
@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name="store_idx")
    private Integer storeIdx;

    private String name;

    private String description;

    private String sub_description;

    private Integer category_id;

    private String category_name;

    private Byte state_active;

    private Byte type;

    private Integer cnt_like;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Integer sequence;

    @Builder
    public Product(Integer storeIdx, String name, String description, String sub_description, Integer category_id, String category_name, Byte state_active, Byte type, Integer cnt_like, Timestamp register_date, Timestamp modify_date, Integer sequence) {
        this.storeIdx = storeIdx;
        this.name = name;
        this.description = description;
        this.sub_description = sub_description;
        this.category_id = category_id;
        this.category_name = category_name;
        this.state_active = state_active;
        this.type = type;
        this.cnt_like = cnt_like;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.sequence = sequence;
    }
}
