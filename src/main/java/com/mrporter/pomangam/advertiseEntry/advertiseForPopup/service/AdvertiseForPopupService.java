package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.service;

import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain.AdvertiseForPopupDto;

import java.util.List;

public interface AdvertiseForPopupService {
    List<AdvertiseForPopupDto> getAdvertisePopupsByDeliverySiteIdx(Integer delivery_site_idx);
}
