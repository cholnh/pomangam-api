package com.mrporter.pomangam.client.services.notice;

import com.mrporter.pomangam.client.domains.notice.Notice;
import com.mrporter.pomangam.client.domains.notice.NoticeDto;
import com.mrporter.pomangam.client.domains.notice.NoticeViewDto;
import com.mrporter.pomangam.client.repositories.notice.NoticeJpaRepository;
import com.mrporter.pomangam.client.services.notice.exception.NoticeException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private NoticeJpaRepository noticeRepo;

    @Override
    public List<NoticeViewDto> findByIdxDeliverySiteWithoutContents(Long dIdx, Pageable pageable) {
        List<Notice> notices = noticeRepo.findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(dIdx, pageable).getContent();
        return NoticeViewDto.fromEntities(notices);
    }

    @Override
    public NoticeDto findByIdx(Long nIdx) {
        return NoticeDto.fromEntity(noticeRepo
                .findByIdxAndIsActiveIsTrue(nIdx)
                .orElseThrow(() -> new NoticeException("invalid event index.")));
    }
}