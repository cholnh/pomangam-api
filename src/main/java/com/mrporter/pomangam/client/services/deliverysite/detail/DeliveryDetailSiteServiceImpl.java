package com.mrporter.pomangam.client.services.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSiteDto;
import com.mrporter.pomangam.client.repositories.deliverysite.detail.DeliveryDetailJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryDetailSiteServiceImpl implements DeliveryDetailSiteService {
    DeliveryDetailJpaRepository deliveryDetailRepository;

    public List<DeliveryDetailSiteDto> getByIdxDeliverySite(Integer didx) {
        List<DeliveryDetailSite> deliveryDetailSites = deliveryDetailRepository.findByDeliverySite_Idx(didx);
        return DeliveryDetailSiteDto.fromEntities(deliveryDetailSites);
    }

    public DeliveryDetailSiteDto getByIdx(Integer idx) {
        DeliveryDetailSite entity = deliveryDetailRepository.findById(idx).get();
        return DeliveryDetailSiteDto.fromEntity(entity);
    }

    public long count() {
        return deliveryDetailRepository.count();
    }
}
