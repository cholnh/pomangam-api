package com.mrporter.pomangam.storeEntry.store.repository;

import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository {
    List<InquiryResultDto> getInquiryResult(@Param("date") String arrival_date,
                                            @Param("idx") Integer delivery_site_idx);
}
