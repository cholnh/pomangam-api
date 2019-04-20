package com.mrporter.pomangam.orderEntry.cartItem.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CartItemInputDto implements Serializable {

    private Integer customerIdx;

    private Integer detailForDeliverySiteIdx;

    private LocalDateTime arrivalDate;

    private CartItemDto mainItem;

}