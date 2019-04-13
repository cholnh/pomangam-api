package com.mrporter.pomangam.view.main.domain;

import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.domain.CmtAdvertiseForMainWithCommentAllDto;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MainSecondViewDto {
    private List<CmtAdvertiseForMainWithCommentAllDto> cmtAdvertiseForMainWithCommentAllDtoList;

    private List<SubAdvertiseForMainDto> subAdvertiseForMainDtoList;
}
