package com.mrporter.pomangam.test.data.point;

import com.mrporter.pomangam.client.domains.user.point.rank.PointRank;
import com.mrporter.pomangam.client.repositories.user.point.rank.PointRankJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PointRankData {

    @Autowired
    PointRankJpaRepository pointRankJpaRepository;

    @Transactional
    public void of(Long idx, String title, int level, int percentSavePoint, int priceSavePoint, int rewardCouponPrice, int nextLowerLimitOrderCount, int nextLowerLimitRecommendCount) {
        PointRank pointRank = PointRank.builder()
                .idx(idx)
                .level((short) level)
                .title(title)
                .nextLowerLimitOrderCount(nextLowerLimitOrderCount)
                .nextLowerLimitRecommendCount(nextLowerLimitRecommendCount)
                .percentSavePoint(percentSavePoint)
                .priceSavePoint(priceSavePoint)
                .rewardCouponPrice(rewardCouponPrice)
                .build();
        pointRankJpaRepository.save(pointRank);
    }
}