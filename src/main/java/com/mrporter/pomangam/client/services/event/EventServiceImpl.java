package com.mrporter.pomangam.client.services.event;

import com.mrporter.pomangam.client.domains.event.Event;
import com.mrporter.pomangam.client.domains.event.EventDto;
import com.mrporter.pomangam.client.domains.event.EventViewDto;
import com.mrporter.pomangam.client.repositories.event.EventJpaRepository;
import com.mrporter.pomangam.client.services.event.exception.EventException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private EventJpaRepository eventRepo;

    @Override
    public List<EventViewDto> findByIdxDeliverySiteWithoutContents(Long dIdx, Pageable pageable) {
        List<Event> events = eventRepo.findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(dIdx, pageable).getContent();
        return EventViewDto.fromEntities(events);
    }

    @Override
    public EventDto findByIdx(Long eIdx) {
        return EventDto.fromEntity(eventRepo
                .findByIdxAndIsActiveIsTrue(eIdx)
                .orElseThrow(() -> new EventException("invalid event index.")));
    }
}