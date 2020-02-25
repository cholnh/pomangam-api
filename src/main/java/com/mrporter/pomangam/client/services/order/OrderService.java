package com.mrporter.pomangam.client.services.order;

import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.OrderType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    /**
     * 유저 핸드폰 번호로 주문내역 리스트 반환
     *
     * @param phoneNumber 유저 핸드폰 번호 (id)
     * @param pageable
     * @return 유저에 해당하는 주문내역 리스트
     */
    List<OrderResponseDto> findByPhoneNumber(String phoneNumber, Pageable pageable);

    /**
     * 주문 내역 저장
     * PG 결제 검증을 위해 사전 정보를 저장
     *
     * @param dto 저장될 주문 내역 request dto
     * @return 저장 완료된 주문 내역 response dto
     */
    OrderResponseDto save(OrderRequestDto dto);

    /**
     * 주문 내역 검증
     * PG 결제 검증 진행.
     *
     * @param oIdx 검증할 주문 인덱스
     * @return 검증 결과
     */
    boolean verify(Long oIdx);

    /**
     * 주문 내역 취소
     *
     * @param oIdx 취소할 주문 인덱스
     */
    void cancel(Long oIdx);

    /**
     * 주문 내역 상태 로깅
     *
     * @param idxOrder 주문 인덱스
     * @param orderType 주문 내역 상태 리스트 (순차적으로 저장)
     */
    void log(Long idxOrder, OrderType ...orderType);
}
