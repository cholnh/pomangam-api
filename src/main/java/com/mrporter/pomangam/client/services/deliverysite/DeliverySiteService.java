package com.mrporter.pomangam.client.services.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeliverySiteService {
    List<DeliverySiteDto> findAll(Pageable pageable);
    DeliverySiteDto findByIdx(Long dIdx);
    long count();
}
