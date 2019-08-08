package com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ImageForCommentAllMainDto implements Serializable {

    private Integer idx;

    private String imgpath;

    private Integer comment_all_idx;

    private Byte state_active;

    private Integer sequence;

    public ImageForCommentAllMainDto(Integer idx, String imgpath, Integer comment_all_idx, Byte state_active, Integer sequence) {
        this.idx = idx;
        this.imgpath = imgpath;
        this.comment_all_idx = comment_all_idx;
        this.state_active = state_active;
        this.sequence = sequence;
    }

    public ImageForCommentAllMain toEntity() {
        return null;
    }
}