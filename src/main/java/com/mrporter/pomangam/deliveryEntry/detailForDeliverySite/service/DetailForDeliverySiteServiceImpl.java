package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.service;

import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailForDeliverySiteServiceImpl implements DetailForDeliverySiteService {

    DetailForDeliverySiteRepositoryImpl detailForDeliverySiteRepository;

    @Override
    public List<DetailForDeliverySiteDto> findByDeliverySiteIdxOrderBySequence(Integer delivery_site_idx) {
        return detailForDeliverySiteRepository.getByDeliverySiteIdxOrderBySequence(delivery_site_idx);
    }
}
