package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartTimeMapDto implements Serializable {
    Integer store_idx;
    LocalDateTime arrivalTime;
    Integer remaining_capacity;
}