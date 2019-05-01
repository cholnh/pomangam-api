package com.mrporter.pomangam.promotionEntry.event.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.event.domain.EventResponseDto;
import com.mrporter.pomangam.promotionEntry.event.repository.EventRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    EventRepositoryImpl eventRepository;

    @Override
    public List<EventResponseDto> getInProgress(Integer delivery_site_idx) {
        return eventRepository.getInProgress(delivery_site_idx);
    }

    @Override
    public List<EventResponseDto> getFinished(Integer delivery_site_idx, PageRequest pageRequest) {
        return eventRepository.getFinished(delivery_site_idx, pageRequest);
    }
}
