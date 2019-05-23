package com.mrporter.pomangam.feedbackHistory.imageForCommentAll.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "imgpath_for_comment_all_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ImageForCommentAll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "comment_all_idx")
    private Integer commentAllIdx;

    private String imgpath;

    private Byte type;

    @Builder
    public ImageForCommentAll(Integer commentAllIdx, String imgpath, Byte type) {
        this.commentAllIdx = commentAllIdx;
        this.imgpath = imgpath;
        this.type = type;
    }
}
