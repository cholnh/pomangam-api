package com.mrporter.pomangam.test.data.point;

import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.client.repositories.user.point.log.PointLogJpaRepository;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PointLogData {

    @Autowired
    PointLogJpaRepository pointLogJpaRepository;

    @Autowired
    UserServiceImpl userService;

    @Transactional
    public void plus(Long uIdx, int point, PointType pointType, Long oIdx) {
        userService.plusPointByIdx(uIdx, point, pointType, oIdx);
    }

    @Transactional
    public void minus(Long uIdx, int point, PointType pointType, Long oIdx) {
        userService.minusPointByIdx(uIdx, point, pointType, oIdx);
    }
}
