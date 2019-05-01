package com.mrporter.pomangam.promotionEntry.notice.controller;

import com.mrporter.pomangam.promotionEntry.notice.domain.Notice;
import com.mrporter.pomangam.promotionEntry.notice.repository.NoticeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notices")
@Controller
@AllArgsConstructor
public class NoticeViewController {

    NoticeJpaRepository noticeJpaRepository;

    @GetMapping("/{noticeIdx}/view")
    public String getView(@PathVariable(name = "noticeIdx") Integer noticeIdx,
                          Model model) {

        Notice notice = noticeJpaRepository.getOne(noticeIdx);
        model.addAttribute("title", notice.getTitle());
        model.addAttribute("contents", notice.getContents());
        String url = notice.getUrl();
        return "notice/" + (url == null || url.isEmpty() ? "default" : url);
    }
}
