package com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CmtAdvertiseForMainWithCommentAllDto implements Serializable {

    private Integer comment_all_idx;

    private Integer store_idx;

    private String title;

    private String contents;

    private Byte c_state_active;

    private String imgpath;

    private Byte a_state_active;

    private Integer sequence;

    public CmtAdvertiseForMainWithCommentAllDto(Integer comment_all_idx, Integer store_idx, String title, String contents, Byte c_state_active, String imgpath, Byte a_state_active, Integer sequence) {
        this.comment_all_idx = comment_all_idx;
        this.store_idx = store_idx;
        this.title = title;
        this.contents = contents;
        this.c_state_active = c_state_active;
        this.imgpath = imgpath;
        this.a_state_active = a_state_active;
        this.sequence = sequence;
    }
}
