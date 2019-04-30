package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ehowlsla on 2018. 5. 29..
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResToken extends ResDefault {
    public ResTokenData data;
}
