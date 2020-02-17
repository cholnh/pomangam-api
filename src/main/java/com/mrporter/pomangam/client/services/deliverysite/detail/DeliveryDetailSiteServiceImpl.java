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

    @Override
    public List<DeliveryDetailSiteDto> findByIdxDeliverySite(Long dIdx) {
        List<DeliveryDetailSite> deliveryDetailSites = deliveryDetailRepository.findByDeliverySite_Idx(dIdx);
        return DeliveryDetailSiteDto.fromEntities(deliveryDetailSites);
    }

    @Override
    public DeliveryDetailSiteDto findByIdx(Long idx) {
        DeliveryDetailSite entity = deliveryDetailRepository.findById(idx).get();
        return DeliveryDetailSiteDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return deliveryDetailRepository.count();
    }
}
