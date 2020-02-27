package com.mrporter.pomangam.client.controllers.event;

import com.mrporter.pomangam.client.domains.event.EventDto;
import com.mrporter.pomangam.client.domains.event.EventViewDto;
import com.mrporter.pomangam.client.services.event.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dsites/{dIdx}/events")
@AllArgsConstructor
public class EventController {

    private EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<EventViewDto>> findByIdxDeliverySiteWithoutContents(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(eventService.findByIdxDeliverySiteWithoutContents(dIdx, pageable), HttpStatus.OK);
    }

    @GetMapping("/{eIdx}")
    public ResponseEntity<EventDto> findByIdx(
            @PathVariable(value = "eIdx", required = true) Long eIdx
    ) {
        return new ResponseEntity(eventService.findByIdx(eIdx), HttpStatus.OK);
    }
}