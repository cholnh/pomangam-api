package com.mrporter.pomangam._bases.utils.bootpay.model.response.verify;

import com.mrporter.pomangam._bases.utils.bootpay.model.response.ResDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VerifyResponse extends ResDefault {
    VerifyData data;
}
