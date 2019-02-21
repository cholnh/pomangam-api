package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class AdvertiseForPopupDto implements Serializable {

    private Integer idx;

    private String imgpath;

    private Byte next_step_type;

    private String next_step_location;

    private Byte state_active;

    private Integer sequence;

    public AdvertiseForPopupDto(Integer idx, String imgpath, Byte next_step_type, String next_step_location, Byte state_active, Integer sequence) {
        this.idx = idx;
        this.imgpath = imgpath;
        this.next_step_type = next_step_type;
        this.next_step_location = next_step_location;
        this.state_active = state_active;
        this.sequence = sequence;
    }

    public AdvertiseForPopup toEntity() {
        return null;
    }
}
