package com.mrporter.pomangam.promotionEntry.pointLog.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLogDto;

import java.util.List;

public interface PointLogRepository {
    PointLogDto getLastNode(Integer customerIdx);
    List<PointLogDto> findByCustomerIdx(Integer customerIdx, PageRequest pageRequest);
}
