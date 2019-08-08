package com.mrporter.pomangam.view.main.domain;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.domain.ImageForCommentAllMainWithCommentAllDto;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MainSecondViewDto {
    private List<ImageForCommentAllMainWithCommentAllDto> imageForCommentAllMainWithCommentAllDtos;

    private List<SubAdvertiseForMainDto> subAdvertiseForMainDtoList;
}
