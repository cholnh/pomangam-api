package com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "advertise_for_main_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class AdvertiseForMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String imgpath;

    private Byte next_step_type;

    private String next_step_location;

    private Byte state_active;

    private Integer sequence;

    @Builder
    public AdvertiseForMain(String imgpath, Byte next_step_type, String next_step_location, Byte state_active, Integer sequence) {
        this.imgpath = imgpath;
        this.next_step_type = next_step_type;
        this.next_step_location = next_step_location;
        this.state_active = state_active;
        this.sequence = sequence;
    }
}
