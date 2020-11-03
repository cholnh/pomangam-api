package com.mrporter.pomangam.client.services.order;

import com.mrporter.pomangam._bases.utils.bootpay.model.response.callback.CallbackResponse;
import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.bootpay.BootpayVbankDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    /**
     * Fcm Token Index 로 주문내역 리스트 반환
     *
     * @param fIdx Fcm Token Index
     * @param pageable
     * @return 유저에 해당하는 주문내역 리스트
     */
    List<OrderResponseDto> findAllByIdxFcmToken(Long fIdx, Pageable pageable);
    List<OrderResponseDto> findTodayByIdxFcmToken(Long fIdx, Pageable pageable);

    List<OrderResponseDto> findAllByPhoneNumber(String phoneNumber, Pageable pageable);
    List<OrderResponseDto> findTodayByPhoneNumber(String phoneNumber, Pageable pageable);

    /**
     * 주문 내역 저장
     * PG 결제 검증을 위해 사전 정보를 저장
     *
     * @param dto 저장될 주문 내역 request dto
     * @return 저장 완료된 주문 내역 response dto
     */
    OrderResponseDto save(OrderRequestDto dto);

    OrderResponseDto patchDetailSite(Long oIdx, Long ddIdx);

    /**
     * 주문 내역 검증
     * PG 결제 검증 진행.
     *
     * @param oIdx 검증할 주문 인덱스
     * @return 검증 결과
     */
    boolean verify(Long oIdx, String receipt_id);

    BootpayVbankDto getVbank(Long oIdx);
    BootpayVbankDto postVbank(BootpayVbankDto dto);
    void postReceipt(Long oIdx, String receiptId);

    /**
     * 주문 내역 승인
     *
     * @param oIdx 승인할 주문 인덱스
     */
    OrderResponseDto approve(Long oIdx);

    /**
     * 주문 내역 거절
     *
     * @param oIdx 승인할 주문 인덱스
     * @param reason 거절 사유
     */
    OrderResponseDto disapprove(Long oIdx, String reason);

    /**
     * 주문 내역 취소
     *
     * @param oIdx 취소할 주문 인덱스
     */
    void cancel(Long oIdx);

    void paymentFail(Long oIdx);

    /**
     * 주문 내역 환불
     * 업주가 진행
     *
     * @param oIdx 환불할 주문 인덱스
     */
    void refund(Long oIdx);

    OrderResponseDto deliveryPickup(Long oIdx);

    OrderResponseDto deliveryDelay(Long oIdx, int min, String reason);

    OrderResponseDto deliverySuccess(Long oIdx);

    void callback(CallbackResponse response);

}
