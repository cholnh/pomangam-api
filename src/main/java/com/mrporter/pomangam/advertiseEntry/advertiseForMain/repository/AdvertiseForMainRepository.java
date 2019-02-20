package com.mrporter.pomangam.advertiseEntry.advertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMainDto;

import java.util.List;

public interface AdvertiseForMainRepository {
    List<AdvertiseForMainDto> getAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx);
}
