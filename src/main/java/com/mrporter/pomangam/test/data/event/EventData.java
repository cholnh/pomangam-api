package com.mrporter.pomangam.test.data.event;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.event.Event;
import com.mrporter.pomangam.client.domains.event.EventMapper;
import com.mrporter.pomangam.client.repositories.event.EventJpaRepository;
import com.mrporter.pomangam.client.repositories.event.EventMapperJpaRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class EventData {

    @Autowired
    EventJpaRepository eventJpaRepository;
    @Autowired
    EventMapperJpaRepository eventMapperJpaRepository;

    @Transactional
    public void of(Long idx, Long dIdx, String title, String contents, LocalDateTime begin, LocalDateTime end) {
        Event event = Event.builder()
                .idx(idx)
                .beginDate(begin)
                .endDate(end)
                .title(title)
                .contents(contents)
                .build();
        eventJpaRepository.save(event);

        EventMapper eventMapper = EventMapper.builder()
                .deliverySite(DeliverySite.builder().idx(dIdx).build())
                .event(event)
                .build();
        eventMapperJpaRepository.save(eventMapper);
    }
}