package com.mrporter.pomangam.client.services.user.point.rank;

import com.mrporter.pomangam.client.domains.user.point.rank.PointRankDto;
import com.mrporter.pomangam.client.repositories.user.point.rank.PointRankJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PointRankServiceImpl implements PointRankService {

    PointRankJpaRepository pointRankRepo;

    public List<PointRankDto> findAll() {
        return PointRankDto.fromEntities(pointRankRepo.findAll());
    }
}
