package com.mrporter.pomangam.productEntry.imageForProduct.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "imgpath_for_product_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ImageForProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer product_idx;

    private String imgpath;

    private Byte type;

    @Builder
    public ImageForProduct(Integer product_idx, String imgpath, Byte type) {
        this.product_idx = product_idx;
        this.imgpath = imgpath;
        this.type = type;
    }
}
