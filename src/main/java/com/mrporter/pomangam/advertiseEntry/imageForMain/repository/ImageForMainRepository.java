package com.mrporter.pomangam.advertiseEntry.imageForMain.repository;

import com.mrporter.pomangam.advertiseEntry.imageForMain.domain.ImageForMainDto;

import java.util.List;

public interface ImageForMainRepository {
    List<ImageForMainDto> getImagesByDeliverySiteIdx(Integer delivery_site_idx);
}
