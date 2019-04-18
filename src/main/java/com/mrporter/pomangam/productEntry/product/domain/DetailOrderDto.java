package com.mrporter.pomangam.productEntry.product.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class DetailOrderDto implements Serializable {

    ProductWithCostDto productWithCostDto;

    List<AdditionalDto> subMenuList;

    List<AdditionalDto> toppingMenuList;

    List<AdditionalDto> beverageMenuList;

}
