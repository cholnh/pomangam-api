package com.mrporter.pomangam.client.services.notice;

import com.mrporter.pomangam.client.domains.notice.NoticeDto;
import com.mrporter.pomangam.client.domains.notice.NoticeViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    List<NoticeViewDto> findByIdxDeliverySiteWithoutContents(Long dIdx, Pageable pageable);
    NoticeDto findByIdx(Long nIdx);
}
