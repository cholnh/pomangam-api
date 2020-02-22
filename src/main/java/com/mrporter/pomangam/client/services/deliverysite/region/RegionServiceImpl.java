package com.mrporter.pomangam.client.services.deliverysite.region;

import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.deliverysite.region.RegionDto;
import com.mrporter.pomangam.client.repositories.deliverysite.region.RegionJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {

    RegionJpaRepository regionRepo;

    @Override
    public List<RegionDto> findAll(Pageable pageable) {
        List<Region> regions = regionRepo.findAllByIsActiveIsTrue(pageable).getContent();
        return RegionDto.fromEntities(regions);
    }

    @Override
    public RegionDto findByIdx(Long idx) {
        Region entity = regionRepo.findByIdxAndIsActiveIsTrue(idx);
        return RegionDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return regionRepo.countByIsActiveIsTrue();
    }
}
