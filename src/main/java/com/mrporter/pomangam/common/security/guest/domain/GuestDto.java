package com.mrporter.pomangam.common.security.guest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class GuestDto implements Serializable {

    private Integer idx;

    private Timestamp registerDate;

    public GuestDto(Integer idx, Timestamp registerDate) {
        this.idx = idx;
        this.registerDate = registerDate;
    }
}