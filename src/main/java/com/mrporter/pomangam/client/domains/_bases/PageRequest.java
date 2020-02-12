package com.mrporter.pomangam.client.domains._bases;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
@Deprecated
public class PageRequest {
    int page;
    int size;
    Sort.Direction sort;
    String by;

    public PageRequest() {
        size = 0;
    }

    public PageRequest(int page, int size) {
        page = page < 0 ? 0 : page;
        size = size < 0 ? 0 : size;
        size = size > 50 ? 50 : size;
        this.page = page;
        this.size = size;
    }

    public PageRequest(int page, int size, Sort.Direction sort, String by) {
        this(page, size);
        this.sort = sort;
        this.by = by;
    }

    public int getFirstIndex() {
        return page * size;
    }

    public org.springframework.data.domain.PageRequest of() {
        if(size == 0) {
            return null;
        } else if(sort == null || by == null) {
            return org.springframework.data.domain.PageRequest.of(page, size);
        } else {
            return org.springframework.data.domain.PageRequest.of(page, size, sort, by);
        }
    }
}
