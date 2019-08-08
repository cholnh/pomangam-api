package com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "imgpath_for_comment_all_main_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ImageForCommentAllMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String imgpath;

    private Integer comment_all_idx;

    private Byte state_active;

    private Integer sequence;

    @Builder
    public ImageForCommentAllMain(String imgpath, Integer comment_all_idx, Byte state_active, Integer sequence) {
        this.imgpath = imgpath;
        this.comment_all_idx = comment_all_idx;
        this.state_active = state_active;
        this.sequence = sequence;
    }
}
