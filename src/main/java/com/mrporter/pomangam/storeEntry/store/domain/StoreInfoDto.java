package com.mrporter.pomangam.storeEntry.store.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class StoreInfoDto implements Serializable {
    private Integer idx;

    private Integer store_idx;

    private String intro;

    private String tip;

    private String owner_name;

    private String company_name;

    private String company_location;

    private String company_no;

    @Builder
    public StoreInfoDto(Integer idx, Integer store_idx, String intro, String tip, String owner_name, String company_name, String company_location, String company_no) {
        this.idx = idx;
        this.store_idx = store_idx;
        this.intro = intro;
        this.tip = tip;
        this.owner_name = owner_name;
        this.company_name = company_name;
        this.company_location = company_location;
        this.company_no = company_no;
    }
}
