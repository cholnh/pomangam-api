package com.mrporter.pomangam.test.data.notice;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.notice.Notice;
import com.mrporter.pomangam.client.domains.notice.NoticeMapper;
import com.mrporter.pomangam.client.repositories.notice.NoticeJpaRepository;
import com.mrporter.pomangam.client.repositories.notice.NoticeMapperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class NoticeData {

    @Autowired
    NoticeJpaRepository noticeJpaRepository;
    @Autowired
    NoticeMapperJpaRepository noticeMapperJpaRepository;

    @Transactional
    public void of(Long idx, Long dIdx, String title, String contents, LocalDateTime begin, LocalDateTime end) {
        Notice notice = Notice.builder()
                .idx(idx)
                .beginDate(begin)
                .endDate(end)
                .title(title)
                .contents(contents)
                .build();
        noticeJpaRepository.save(notice);

        NoticeMapper noticeMapper = NoticeMapper.builder()
                .deliverySite(DeliverySite.builder().idx(dIdx).build())
                .notice(notice)
                .build();
        noticeMapperJpaRepository.save(noticeMapper);
    }
}