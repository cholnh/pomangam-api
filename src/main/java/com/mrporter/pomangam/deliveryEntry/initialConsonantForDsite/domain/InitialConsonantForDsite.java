package com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "initial_consonant_for_dsite_tbl")
@NoArgsConstructor
@Data
@Entity
public class InitialConsonantForDsite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String initial_consonant;

    private Integer delivery_site_idx;

    @Builder
    public InitialConsonantForDsite(String initial_consonant, Integer delivery_site_idx) {
        this.initial_consonant = initial_consonant;
        this.delivery_site_idx = delivery_site_idx;
    }
}
