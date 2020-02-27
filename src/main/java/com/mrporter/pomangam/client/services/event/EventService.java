package com.mrporter.pomangam.client.services.event;

import com.mrporter.pomangam.client.domains.event.EventDto;
import com.mrporter.pomangam.client.domains.event.EventViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    List<EventViewDto> findByIdxDeliverySiteWithoutContents(Long dIdx, Pageable pageable);
    EventDto findByIdx(Long eIdx);
}
