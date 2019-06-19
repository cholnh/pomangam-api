package com.mrporter.pomangam.promotionEntry.notice.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.notice.domain.Notice;
import com.mrporter.pomangam.promotionEntry.notice.domain.NoticeResponseDto;
import com.mrporter.pomangam.promotionEntry.notice.repository.NoticeJpaRepository;
import com.mrporter.pomangam.promotionEntry.notice.repository.NoticeRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    NoticeJpaRepository noticeJpaRepository;
    NoticeRepositoryImpl noticeRepository;

    @Override
    public NoticeResponseDto get(Integer noticeIdx) {
        Optional<Notice> optional = noticeJpaRepository.findById(noticeIdx);
        if(optional.isPresent()) {
            Notice notice = optional.get();
            NoticeResponseDto dto = NoticeResponseDto.builder()
                    .idx(notice.getIdx())
                    .title(notice.getTitle())
                    .begin_date(notice.getBegin_date())
                    .end_date(notice.getEnd_date())
                    .url(notice.getUrl())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<NoticeResponseDto> getAll(Integer delivery_site_idx, PageRequest pageRequest) {
        return noticeRepository.getAll(delivery_site_idx, pageRequest);
    }

}
