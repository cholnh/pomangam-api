package com.mrporter.pomangam.promotionEntry.notice.controller;

import com.mrporter.pomangam.promotionEntry.notice.service.NoticeServiceImpl;
import lombok.AllArgsConstructor;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notices")
@RestController
@AllArgsConstructor
public class NoticeController {

    NoticeServiceImpl noticeService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam("deliverySiteIdx") Integer delivery_site_idx,
                                        PageRequest pageRequest) {
        return new ResponseEntity(noticeService.getAll(delivery_site_idx, pageRequest), HttpStatus.OK);
    }
}
