package com.mrporter.pomangam.storeEntry.imageForStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "imgpath_for_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ImageForStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer store_idx;

    private String imgpath;

    private Byte type;

    @Builder
    public ImageForStore(Integer store_idx, String imgpath, Byte type) {
        this.store_idx = store_idx;
        this.imgpath = imgpath;
        this.type = type;
    }
}
