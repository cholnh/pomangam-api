package com.mrporter.pomangam.promotionEntry.pointLog.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PointLogDto implements Serializable {

    private Integer idx;

    private Integer customer_idx;

    private Integer order_idx;

    private Date register_date;

    private Integer final_prc;

    private Integer use_point;

    private Byte type;

    public PointLog toEntity() {
        return null;
    }
}
