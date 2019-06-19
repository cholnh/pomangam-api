package com.mrporter.pomangam.promotionEntry.event.controller;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.event.service.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/events")
@RestController
@AllArgsConstructor
public class EventController {

    EventServiceImpl eventService;

    @GetMapping("/{eventIdx}")
    public ResponseEntity get(@PathVariable(value = "eventIdx") Integer eventIdx) {
        return new ResponseEntity(eventService.get(eventIdx), HttpStatus.OK);
    }

    @GetMapping("/getInProgress")
    public ResponseEntity getInProgress(@RequestParam(value = "deliverySiteIdx", required = false) Integer delivery_site_idx) {
        return new ResponseEntity(eventService.getInProgress(delivery_site_idx), HttpStatus.OK);
    }

    @GetMapping("/getFinished")
    public ResponseEntity getFinished(@RequestParam(value = "deliverySiteIdx", required = false) Integer delivery_site_idx,
                                      PageRequest pageRequest) {
        return new ResponseEntity(eventService.getFinished(delivery_site_idx, pageRequest), HttpStatus.OK);
    }
}
