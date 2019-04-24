package com.mrporter.pomangam.promotionEntry.pointLog.repository;

import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLogDto;

public interface PointLogRepository {
    PointLogDto getLastNode(Integer customerIdx);
}
