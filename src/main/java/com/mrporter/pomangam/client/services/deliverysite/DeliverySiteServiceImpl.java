package com.mrporter.pomangam.client.services.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import com.mrporter.pomangam.client.repositories.deliverysite.DeliverySiteJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliverySiteServiceImpl implements DeliverySiteService {

    DeliverySiteJpaRepository deliverySiteRepository;

    @Override
    public List<DeliverySiteDto> findAll(Pageable pageable) {
        List<DeliverySite> deliverySites = deliverySiteRepository.findAllByIsActiveIsTrue(pageable).getContent();
        return DeliverySiteDto.fromEntities(deliverySites);
    }

    @Override
    public DeliverySiteDto findByIdx(Long dIdx) {
        DeliverySite entity = deliverySiteRepository.findByIdxAndIsActiveIsTrue(dIdx);
        return DeliverySiteDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return deliverySiteRepository.countByIsActiveIsTrue();
    }
}
