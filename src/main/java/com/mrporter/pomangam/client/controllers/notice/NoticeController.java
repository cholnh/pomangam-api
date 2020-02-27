package com.mrporter.pomangam.client.controllers.notice;

import com.mrporter.pomangam.client.domains.event.EventDto;
import com.mrporter.pomangam.client.services.notice.NoticeServiceImpl;
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

@RestController
@RequestMapping("/dsites/{dIdx}/notices")
@AllArgsConstructor
public class NoticeController {

    private NoticeServiceImpl noticeService;

    @GetMapping
    public ResponseEntity<?> findByIdxDeliverySiteWithoutContents(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(noticeService.findByIdxDeliverySiteWithoutContents(dIdx, pageable), HttpStatus.OK);
    }

    @GetMapping("/{nIdx}")
    public ResponseEntity<EventDto> findByIdx(
            @PathVariable(value = "nIdx", required = true) Long nIdx
    ) {
        return new ResponseEntity(noticeService.findByIdx(nIdx), HttpStatus.OK);
    }
}