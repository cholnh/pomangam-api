package com.mrporter.pomangam.promotionEntry.event.controller;

import com.mrporter.pomangam.promotionEntry.event.domain.Event;
import com.mrporter.pomangam.promotionEntry.event.repository.EventJpaRepository;
import com.mrporter.pomangam.promotionEntry.event.service.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/events")
@Controller
@AllArgsConstructor
public class EventViewController {

    EventServiceImpl eventService;
    EventJpaRepository eventJpaRepository;

    @GetMapping("/{eventIdx}/view")
    public String getView(@PathVariable(name = "eventIdx") Integer eventIdx,
                          Model model) {

        Event event = eventJpaRepository.getOne(eventIdx);
        model.addAttribute("title", event.getTitle());
        model.addAttribute("contents", event.getContents());
        String url = event.getUrl();
        return "event/" + (url == null || url.isEmpty() ? "default" : url);
    }
}
