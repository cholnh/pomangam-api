package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.repository;

import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain.AdvertiseForPopupDto;

import java.util.List;

public interface AdvertiseForPopupRepository {
    List<AdvertiseForPopupDto> getAdvertisePopupsByDeliverySiteIdx(Integer delivery_site_idx);
}
