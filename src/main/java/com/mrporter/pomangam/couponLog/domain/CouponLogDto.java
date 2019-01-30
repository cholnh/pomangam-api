package com.mrporter.pomangam.couponLog.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CouponLogDto implements Serializable {

    private Long idx;

    public CouponLog toEntity() {
        return null;
    }
}