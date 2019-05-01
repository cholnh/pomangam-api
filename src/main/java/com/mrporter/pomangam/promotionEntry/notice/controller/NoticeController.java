package com.mrporter.pomangam.promotionEntry.notice.controller;

import com.mrporter.pomangam.promotionEntry.notice.service.NoticeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notices")
@RestController
@AllArgsConstructor
public class NoticeController {

    NoticeServiceImpl noticeService;
}
