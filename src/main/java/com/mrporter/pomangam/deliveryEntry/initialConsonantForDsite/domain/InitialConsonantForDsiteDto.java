package com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class InitialConsonantForDsiteDto implements Serializable {

    private Integer idx;

    private String initial_consonant;

    private Integer delivery_site_idx;

    public InitialConsonantForDsiteDto(Integer idx, String initial_consonant, Integer delivery_site_idx) {
        this.idx = idx;
        this.initial_consonant = initial_consonant;
        this.delivery_site_idx = delivery_site_idx;
    }
}