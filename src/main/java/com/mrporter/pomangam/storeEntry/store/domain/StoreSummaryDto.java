package com.mrporter.pomangam.storeEntry.store.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StoreSummaryDto {
    private Integer idx;

    private String name;

    private String description;

    private Integer cnt_like;

    private Integer cnt_comment;

    private Integer sequence;

    private Byte type;

    public StoreSummaryDto(Integer idx, String name, String description, Integer cnt_like, Integer cnt_comment, Integer sequence, Byte type) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.cnt_like = cnt_like;
        this.cnt_comment = cnt_comment;
        this.sequence = sequence;
        this.type = type;
    }
}
