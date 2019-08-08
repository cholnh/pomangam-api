package com.mrporter.pomangam.advertiseEntry.advertiseForMain.service;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMainDto;

import java.util.List;

public interface AdvertiseForMainService {
    List<AdvertiseForMainDto> getAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx);
}
