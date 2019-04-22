package com.mrporter.pomangam.orderEntry.payment.domain;

public class PaymentCancelAnnotation {
    String pg_tid; // (string): PG사 승인취소번호 ,
    Double amount; // (number): 취소 금액 ,
    Integer cancelled_at; // (integer): 결제취소된 시각 UNIX timestamp ,
    String reason; // (string): 결제취소 사유 ,
    String receipt_url; // (string, optional): 취소에 대한 매출전표 확인 URL. PG사에 따라 제공되지 않는 경우도 있음
}
