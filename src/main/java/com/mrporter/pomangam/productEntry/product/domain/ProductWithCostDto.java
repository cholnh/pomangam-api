package com.mrporter.pomangam.productEntry.product.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class ProductWithCostDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

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

    private BigInteger prime_cost;

    private BigInteger final_cost;

    private String imgpath;

    public ProductWithCostDto(Integer idx, Integer store_idx, String name, String description, String sub_description, Integer category_id, String category_name, Byte state_active, Byte type, Integer cnt_like, Timestamp register_date, Timestamp modify_date, Integer sequence, BigInteger prime_cost, BigInteger final_cost, String imgpath) {
        this.idx = idx;
        this.store_idx = store_idx;
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
        this.prime_cost = prime_cost;
        this.final_cost = final_cost;
        //this.prime_cost = prime_cost==null?0:prime_cost.intValue();
        //this.final_cost = final_cost==null?0:final_cost.intValue();
        this.imgpath = imgpath;
    }
}