package com.mrporter.pomangam.promotionEntry.pointLog.service;

import com.mrporter.pomangam.promotionEntry.pointLog.domain.StatePointLog;

public interface PointLogService {
    void logUsed(Integer customerIdx, Integer orderIdx, Integer usedPoint, StatePointLog state);
    void logSaved(Integer customerIdx, Integer orderIdx, Integer usedPoint, StatePointLog state);
}
