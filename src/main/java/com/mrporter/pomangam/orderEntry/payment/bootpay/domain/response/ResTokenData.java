package com.mrporter.pomangam.orderEntry.payment.bootpay.domain.response;

import lombok.Data;

/**
 * Created by ehowlsla on 2018. 5. 29..
 */
@Data
public class ResTokenData {
    public String token;
    public long server_time;
    public long expired_at;
}
