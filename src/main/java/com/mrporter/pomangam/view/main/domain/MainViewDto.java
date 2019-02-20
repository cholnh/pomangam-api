package com.mrporter.pomangam.view.main.domain;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMainDto;
import com.mrporter.pomangam.advertiseEntry.imageForMain.domain.ImageForMainDto;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class MainViewDto implements Serializable {
    private List<AdvertiseForMainDto> advertiseForMainList;

    private List<DetailForDeliverySiteDto> detailSiteDtoList;

    private Date arrival_date;

    private List<OrderTimeDto> orderTimeDtoList;

    private List<ImageForMainDto> imageForMainDtoList;

    private List<SubAdvertiseForMainDto> subAdvertiseForMainDtoList;

}
