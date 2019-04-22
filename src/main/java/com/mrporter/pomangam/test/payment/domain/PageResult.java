package com.mrporter.pomangam.test.payment.domain;

public class PageResult<T> {
    T result;
    Page page;
    class Page {
        int size;
        int totalElements;
        int totalPages;
    }
}
