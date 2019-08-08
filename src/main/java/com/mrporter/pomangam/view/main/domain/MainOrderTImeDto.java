package com.mrporter.pomangam.view.main.domain;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class MainOrderTImeDto {
    private List<Hour> hours;

    private Date arrival_date;

    private List<OrderTimeDto> orderTimeDtoList;
}
