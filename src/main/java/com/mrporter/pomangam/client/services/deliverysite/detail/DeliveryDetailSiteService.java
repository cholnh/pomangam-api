package com.mrporter.pomangam.client.services.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSiteDto;

import java.util.List;

public interface DeliveryDetailSiteService {
    List<DeliveryDetailSiteDto> findByIdxDeliverySite(Long dIdx);
    DeliveryDetailSiteDto findByIdx(Long idx);
    long count();
}
