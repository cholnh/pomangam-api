package com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.service;

import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;

import java.util.List;

public interface SubAdvertiseForMainService {
    List<SubAdvertiseForMainDto> getSubAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx);
}
