package com.mrporter.pomangam.promotionEntry.event.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.event.domain.Event;
import com.mrporter.pomangam.promotionEntry.event.domain.EventResponseDto;
import com.mrporter.pomangam.promotionEntry.event.repository.EventJpaRepository;
import com.mrporter.pomangam.promotionEntry.event.repository.EventRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    EventJpaRepository eventJpaRepository;
    EventRepositoryImpl eventRepository;

    @Override
    public EventResponseDto get(Integer eventIdx) {
        Optional<Event> optional = eventJpaRepository.findById(eventIdx);
        if(optional.isPresent()) {
            Event event = optional.get();
            EventResponseDto dto = EventResponseDto.builder()
                    .idx(event.getIdx())
                    .title(event.getTitle())
                    .begin_date(event.getBegin_date())
                    .end_date(event.getEnd_date())
                    .url(event.getUrl())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<EventResponseDto> getInProgress(Integer delivery_site_idx) {
        return eventRepository.getInProgress(delivery_site_idx);
    }

    @Override
    public List<EventResponseDto> getFinished(Integer delivery_site_idx, PageRequest pageRequest) {
        return eventRepository.getFinished(delivery_site_idx, pageRequest);
    }
}
