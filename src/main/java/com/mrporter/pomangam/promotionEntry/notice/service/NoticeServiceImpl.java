package com.mrporter.pomangam.promotionEntry.notice.service;

import com.mrporter.pomangam.promotionEntry.notice.domain.NoticeResponseDto;
import com.mrporter.pomangam.promotionEntry.notice.repository.NoticeRepositoryImpl;
import lombok.AllArgsConstructor;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    NoticeRepositoryImpl noticeRepository;

    @Override
    public List<NoticeResponseDto> getAll(Integer delivery_site_idx, PageRequest pageRequest) {
        return noticeRepository.getAll(delivery_site_idx, pageRequest);
    }
}
