package com.mrporter.pomangam.promotionEntry.event.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.event.domain.EventResponseDto;

import java.util.List;

public interface EventService {
    List<EventResponseDto> getInProgress(Integer delivery_site_idx);
    List<EventResponseDto> getFinished(Integer delivery_site_idx, PageRequest pageRequest);
}
