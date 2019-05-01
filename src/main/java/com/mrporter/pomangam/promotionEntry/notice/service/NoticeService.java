package com.mrporter.pomangam.promotionEntry.notice.service;

import com.mrporter.pomangam.promotionEntry.notice.domain.NoticeResponseDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface NoticeService {
    List<NoticeResponseDto> getAll(Integer delivery_site_idx, PageRequest pageRequest);
}
