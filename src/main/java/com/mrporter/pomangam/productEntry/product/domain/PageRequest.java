package com.mrporter.pomangam.productEntry.product.domain;

import lombok.Data;

@Data
public class PageRequest {
    Integer page;
    Integer size;

    public PageRequest() {
        this.page = 0;
        this.size = 10;
    }

    public PageRequest(int page, int size) {
        page = page < 0 ? 0 : page;
        size = size < 0 ? 0 : size;
        size = size > 50 ? 50 : size;
        this.page = page;
        this.size = size;
    }
}
