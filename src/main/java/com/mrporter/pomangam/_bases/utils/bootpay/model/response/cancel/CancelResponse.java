package com.mrporter.pomangam._bases.utils.bootpay.model.response.cancel;

import com.mrporter.pomangam._bases.utils.bootpay.model.response.ResDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CancelResponse extends ResDefault {
    CancelData data;
}
