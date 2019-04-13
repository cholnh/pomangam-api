package com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.domain.CmtAdvertiseForMainWithCommentAllDto;

import java.util.List;

public interface CmtAdvertiseForMainRepository {
    List<CmtAdvertiseForMainWithCommentAllDto> getCmtAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx);
}
