package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CommentAllViewDto implements Serializable {

    List<StoreCategoryDto> storeCategories;
    List<CommentAllDto> commentAlls;

}