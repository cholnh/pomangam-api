package com.mrporter.pomangam.client.test.error.domain;

import lombok.Data;

@Data
public class DummyData {
    Integer idx;
    String key;
    String val;

    public DummyData(Integer idx, String key, String val) {
        this.idx = idx;
        this.key = key;
        this.val = val;
    }
}
