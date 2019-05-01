package com.mrporter.pomangam.promotionEntry.notice.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.notice.domain.NoticeResponseDto;

import java.util.List;

public interface NoticeRepository {
    List<NoticeResponseDto> getAll(Integer delivery_site_idx, PageRequest pageRequest);
}
