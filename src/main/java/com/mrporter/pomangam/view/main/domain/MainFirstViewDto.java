package com.mrporter.pomangam.view.main.domain;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMainDto;
import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain.AdvertiseForPopupDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class MainFirstViewDto {
    private List<AdvertiseForPopupDto> advertiseForPopupDtoList;

    private List<AdvertiseForMainDto> advertiseForMainDtoList;

    private List<DetailForDeliverySiteDto> detailSiteDtoList;

    private Date arrival_date;

    private List<OrderTimeDto> orderTimeDtoList;
}
