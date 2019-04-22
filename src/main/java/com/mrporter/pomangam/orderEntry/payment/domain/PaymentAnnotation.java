package com.mrporter.pomangam.orderEntry.payment.domain;

import lombok.Data;

import java.util.List;

@Data
public class PaymentAnnotation {
    String imp_uid;    // (string): 아임포트 결제 고유 UID ,
    String merchant_uid;    // (string): 가맹점에서 전달한 거래 고유 java.rmi.server.UID ,
    String pay_method;    // (string, optional): samsung : 삼성페이 / card : 신용카드 / trans : 계좌이체 / vbank : 가상계좌 ,
    String channel;    // (string, optional): 결제가 발생된 경로. pc:(인증방식)PC결제, mobile:(인증방식)모바일결제, api:정기결제 또는 비인증방식결제 = ['pc', 'mobile', 'api'],
    String pg_provider;    // (string, optional): PG사 명칭. inicis(이니시스) / nice(나이스정보통신) ,
    String pg_tid;    // (string, optional): PG사 승인정보 ,
    String pg_id;    // (string, optional): 거래가 처리된 PG사 상점아이디 ,
    Boolean escrow;    // (boolean, optional): 에스크로결제 여부 ,
    String apply_num;    // (string, optional): 카드사 승인정보(계좌이체/가상계좌는 값 없음) ,
    String bank_code;    // (string, optional): 은행 표준코드 - (금융결제원기준) ,
    String bank_name;    // (string, optional): 은행 명칭 - (실시간계좌이체 결제 건의 경우) ,
    String card_code;    // (string, optional): 카드사 코드번호(금융결제원 표준코드번호) = ['361(BC카드)', '364(광주카드)', '365(삼성카드)', '366(신한카드)', '367(현대카드)', '368(롯데카드)', '369(수협카드)', '370(씨티카드)', '371(NH카드)', '372(전북카드)', '373(제주카드)', '374(하나SK카드)', '381(KB국민카드)', '041(우리카드)', '071(우체국)'],
    String card_name;    // (string, optional): 카드사 명칭 - (신용카드 결제 건의 경우) ,
    Integer card_quota;    // (integer, optional): 할부개월 수(0이면 일시불) ,
    String vbank_code;    // (string, optional): 가상계좌 은행 표준코드 - (금융결제원기준) ,
    String vbank_name;    // (string, optional): 입금받을 가상계좌 은행명 ,
    String vbank_num;    // (string, optional): 입금받을 가상계좌 계좌번호 ,
    String vbank_holder;    // (string, optional): 입금받을 가상계좌 예금주 ,
    Integer vbank_date;    // (integer, optional): 입금받을 가상계좌 마감기한 UNIX timestamp ,
    Integer vbank_issued_at;    // (integer, optional): 가상계좌 생성 시각 UNIX timestamp ,
    String name;    // (string, optional): 주문명칭 ,
    Double amount;    // (number): 주문(결제)금액 ,
    Double cancel_amount;    // (number): 결제취소금액 ,
    String currency;    // (string): 결제승인화폐단위(KRW:원, USD:미화달러, EUR:유로) ,
    String buyer_name;    // (string, optional): 주문자명 ,
    String buyer_email;    // (string, optional): 주문자 Email주소 ,
    String buyer_tel;    // (string, optional): 주문자 전화번호 ,
    String buyer_addr;    // (string, optional): 주문자 주소 ,
    String buyer_postcode;    // (string, optional): 주문자 우편번호 ,
    String custom_data;    // (string, optional): 가맹점에서 전달한 custom data. JSON string으로 전달 ,
    String user_agent;    // (string, optional): 구매자가 결제를 시작한 단말기의 UserAgent 문자열 ,
    String status;    // (string): 결제상태. ready:미결제, paid:결제완료, cancelled:결제취소, failed:결제실패 = ['ready', 'paid', 'cancelled', 'failed'],
    Integer paid_at;    // (integer, optional): 결제완료시점 UNIX timestamp. 결제완료가 아닐 경우 0 ,
    Integer failed_at;    // (integer, optional): 결제실패시점 UNIX timestamp. 결제실패가 아닐 경우 0 ,
    Integer cancelled_at;    // (integer, optional): 결제취소시점 UNIX timestamp. 결제취소가 아닐 경우 0 ,
    String fail_reason;    // (string, optional): 결제실패 사유 ,
    String cancel_reason;    // (string, optional): 결제취소 사유 ,
    String receipt_url;    // (string, optional): 신용카드 매출전표 확인 URL ,
    List<PaymentCancelAnnotation> cancel_history;    // (Array[PaymentCancelAnnotation], optional): 취소/부분취소 내역 ,
    List<String> cancel_receipt_urls;    // (Array[string], optional): (Deprecated : cancel_history 사용 권장) 취소/부분취소 시 생성되는 취소 매출전표 확인 URL. 부분취소 횟수만큼 매출전표가 별도로 생성됨 ,
    Boolean cash_receipt_issued;    // (boolean, optional): 현금영수증 자동발급 여부
}
