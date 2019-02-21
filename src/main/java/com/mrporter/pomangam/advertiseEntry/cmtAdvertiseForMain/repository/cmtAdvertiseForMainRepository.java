package com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.domain.cmtAdvertiseForMainWithCommentAllDto;

import java.util.List;

public interface cmtAdvertiseForMainRepository {
    List<cmtAdvertiseForMainWithCommentAllDto> getCmtAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx);
}
