package com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;

import java.util.List;

public interface SubAdvertiseForMainRepository {
    List<SubAdvertiseForMainDto> getSubAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx);
}
