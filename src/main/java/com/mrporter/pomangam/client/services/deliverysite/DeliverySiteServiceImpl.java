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

    public List<DeliverySiteDto> get(Pageable pageable) {
        List<DeliverySite> deliverySites = deliverySiteRepository.findAll(pageable).getContent();
        return DeliverySiteDto.fromEntities(deliverySites);
    }

    public DeliverySiteDto getByIdx(Integer didx) {
        DeliverySite entity = deliverySiteRepository.findById(didx).get();
        return DeliverySiteDto.fromEntity(entity);
    }

    public long count() {
        return deliverySiteRepository.count();
    }
}
