package com.mrporter.pomangam.promotionEntry.notice.controller;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.notice.service.NoticeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notices")
@RestController
@AllArgsConstructor
public class NoticeController {

    NoticeServiceImpl noticeService;

    @GetMapping("/{noticeIdx}")
    public ResponseEntity get(@PathVariable(value = "noticeIdx") Integer noticeIdx) {
        return new ResponseEntity(noticeService.get(noticeIdx), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "deliverySiteIdx", required = false) Integer delivery_site_idx,
                                        PageRequest pageRequest) {
        return new ResponseEntity(noticeService.getAll(delivery_site_idx, pageRequest), HttpStatus.OK);
    }
}
