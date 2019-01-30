package com.mrporter.pomangam.pointLog.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PointLogDto implements Serializable {

    private Long idx;

    private Long customer_idx;

    private Long order_idx;

    private Date register_date;

    private Integer final_prc;

    private Integer use_point;

    private Integer type;

    public PointLog toEntity() {
        return null;
    }
}
