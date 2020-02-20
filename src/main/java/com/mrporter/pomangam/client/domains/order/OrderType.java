package com.mrporter.pomangam.client.domains.order;

/**
 * 주문 프로세스 타입(주문 단계별 명칭)
 * 크게 결제에 관한 상태, 주문에 관한 상태, 배달에 관한 상태 세가지로 구성된다.
 * (t : transition, 예시: t4 결제성공 -> 주문대기)
 * 주문 프로세스 도식화: https://drive.google.com/file/d/1jBNSySyrCOz5KbbKiaWeXYxaxoJcdbyE/view?usp=sharing
 */
public enum OrderType {

    /**
     * 결제 대기
     *
     * 고객이 App 장바구니에 담긴 물품을 확인하고 결제를 누르면 해당 상태로 전이된다. (t0)
     * 고객은 여러 결제수단 (카드결제, 간편결제, 가상계좌 등) 중 하나를 고르고 결제를 진행한다.
     * 이 때, 카드결제와 같이 바로 결제 결과를 알 수 있는 방식과,
     * 무통장 입금과 같이 결제 결과를 기다려야 하는 방식이 존재한다.
     * 결제 결과를 기다리는 동안 주문 상태는 결제 대기 상태에 머무르게 된다.
     * PG사 결제 결과 응답에 따라 성공(t1), 실패(t2), 취소(t3)로 전이된다.
     */
    PAYMENT_READY,

    /**
     * 결제 성공
     *
     * PG 사에서 결제 성공 응답을 받은 경우, 해당 상태로 전이된다. (t1)
     * 성공 상태를 서버에 기록하고 주문 대기 상태로 전이가 진행된다.
     * 고객에게 상태에 대한 notification(알림)이 전송된다.
     */
    PAYMENT_SUCCESS,

    /**
     * 결제 실패
     *
     * PG 사에서 결제 실패 응답을 받은 경우, 해당 상태로 전이된다. (카드잔액부족 등) (t2)
     * 실패 상태를 서버에 기록하고 상태가 종료된다. (터미널)
     * 고객에게 상태, 실패 사유에 대한 notification(알림)이 전송된다.
     */
    PAYMENT_FAIL,

    /**
     * 결제 취소
     *
     * PG 사에서 결제 취소 응답을 받거나 결제 도중 취소한 경우, 해당 상태로 전이된다. (t3)
     * 취소 상태를 서버에 기록하고 상태가 종료된다. (터미널)
     * 고객에게 상태, 취소 사유에 대한 notification(알림)이 전송된다
     */
    PAYMENT_CANCEL,

    /**
     * 결제 환불
     *
     * 이미 결제를 성공하였으나 주문이 취소된 경우, 해당 상태로 전이된다. (t9)
     * 환불에 대한 시나리오는 다음과 같다.
     *  - 업체에서 제작이 불가능하다 판단하여 주문 거절 한 경우 (t6, t8, t23)
     *  - 업체에서 주문을 수락 하였으나 고객이 업체에게 취소를 요청하고 업체측이 취소에 동의한 경우 (t5, t9, t24, t23)
     *  - 업체에서 주문을 실수로 수락한 경우 (t5, t9, t24, t23)
     *  - 업체에서 주문을 수락하기 이전에 고객이 취소를 누른 경우 (t7, t23)
     *  - 배달기사 실수로 인해 제품이 누락되고 고객이 그에 대해 환불을 원할 경우 (t22, t23)
     *  - 업체 실수로 인해 제품이 누락되고 고객이 그에 대해 환불을 원할 경우 (t21, t23)
     * 환불 상태를 서버에 기록하고 상태가 종료된다. (터미널)
     * PG 사에 환불요청을 하여 환불 금액을 환불한다.
     * 고객에게 상태, 환불 사유에 대한 notification(알림)이 전송된다
     */
    PAYMENT_REFUND,

    /**
     * 주문 대기
     *
     * 결제를 성공한 경우, 해당 상태로 자동 전이된다. (t4)
     * 업체가 주문 수락 또는 주문 거절 누르거나 고객이 주문 취소를 누르기 전까지 대기상태에 머무르게 된다.
     * 대기 상태에 5분 이상 머무를 경우, 자동으로 주문이 취소 되며 주문 취소로 전이된다. (t7)
     * 전이 됨과 동시에 취소 사유에 ‘업체 주문 미확인’ 으로 등록된다.
     */
    ORDER_READY,

    /**
     * 주문 퀵 대기
     *
     * 재 배달의 경우 퀵 주문 대기 상태로 전이된다. (t20)
     * 퀵 서비스 배달을 위해 상세주소가 기입되어야 한다.
     * 업체가 주문 수락 또는 주문 거절 누르거나 고객이 주문 취소를 누르기 전까지 대기상태에 머무르게 된다.
     * 대기 상태에 5분 이상 머무를 경우, 자동으로 주문이 취소 되며 주문 취소로 전이된다. (t7)
     * 전이 됨과 동시에 취소 사유에 ‘업체 주문 미확인’ 으로 등록된다.
     */
    ORDER_QUICK_READY,

    /**
     * 주문 성공
     *
     * 업체가 주문을 수락한 경우, 해당 상태로 전이된다. (t5)
     * 업체는 제품을 배달 픽업 시간 내에 제작해야만 한다.
     * 제작한 제품의 겉면에 제품 식별 문자를 기입해야 한다. (스티커 또는 포장봉투 겉면)
     * 상세 위치 별로 나누어서 기사에게 전달한다. (예시: 학생회관, 기숙사식당 별로 제품을 나눔)
     * 성공 상태를 서버에 기록하고 배달 대기 상태로 전이가 진행된다. (t9)
     */
    ORDER_SUCCESS,

    /**
     * 주문 거절
     *
     * 업체가 주문을 거절한 경우, 해당 상태로 전이된다. (t6)
     * 업체가 제품 제작이 불가능 하다고 판단할 경우이다.
     * 업체는 거절에 대한 사유를 기입해야만 한다.
     * 거절 상태를 서버에 기록하고 주문 취소 상태로 전이가 진행된다. (t8)
     * 거절 사유는 주문 취소로 전이 됨과 동시에 전달되어 취소 사유에 등록된다.
     * ( 예시: ‘[업체 주문 거절] 사유: 주문량 대비 인력 부족으로 인해 요리 불가능..’ 등 )
     */
    ORDER_REFUSE,

    /**
     * 주문 취소
     *
     * 주문이 취소된 경우, 해당 상태로 전이된다. (t7, t8, t21, t22, t24)
     * 취소에 대한 시나리오는 결제환불 내 시나리오와 동일하다.
     * 취소 상태를 서버에 기록하고 결제 환불 상태로 전이가 진행된다. (t23)
     * 고객에게 상태, 취소 사유에 대한 notification(알림)이 전송된다
     */
    ORDER_CANCEL,

    /**
     * 배달 대기
     *
     * 주문을 성공한 경우, 해당 상태로 자동 전이된다. (t9)
     * 고객이 업체를 통해 취소를 요청할 경우, 업체가 수락을 하면 주문 취소로 전이 된다. (t24)
     * 또는 업체가 실수로 주문수락을 한 경우, 주문 취소를 할 수 있다. (t24)
     * 취소 사유는 주문 취소로 전이 됨과 동시에 전달되어 취소 사유에 등록된다.
     * 정상적으로 제품에 대한 제작이 되었고, 배달기사가 제품을 전달 받았다면,
     * 배달 픽업 상태로 전이 된다. (t10)
     */
    DELIVERY_READY,

    /**
     * 배달 픽업
     *
     * 배달 기사가 기사App 을 통해 업체 픽업 완료를 누를 경우, 해당 상태로 전이된다. (t10)
     * 기사는 픽업 시간에 맞추어 각 업체들을 방문후 제품을 수령 후, 픽업 완료를 선택해야 한다.
     * 최종 픽업을 마친 경우 배달 장소로 이동, 제품들을 고객별로 식별하여 나누어 주어야 한다.
     */
    DELIVERY_PICKUP,

    /**
     * 배달 지연
     *
     * 배달 기사가 배달 장소까지 제 시간내에 도착하지 못 할 경우이다.
     * 배달 기사가 기사App 을 통해 배달 지연을 누를 경우, 해당 상태로 전이된다. (t11)
     * 기사는 지연에 대한 사유를 기입해야만 한다.
     * 고객에게 상태, 지연 사유에 대한 notification(알림)이 전송된다
     */
    DELIVERY_DELAY,

    /**
     * 배달 성공
     *
     * 배달 기사가 정상적으로 고객에게 제품을 전달하여 배달이 종료된 경우이다.
     * 배달 기사가 기사App 을 통해 배달 완료를 누를 경우, 해당 상태로 전이된다. (t12,t15)
     * 기사 App을 통해 일괄적으로 해당 상태로 전이 할 수 있다.
     * 배달 성공 상태에서도 누락 상태로 전이 될 수 있다. (t16,t17)
     * (예시: 고객에게 전달하였으나, 고객이 제품을 확인하였을 때 메인 제품만 배달되고 서브제품에 대한 부분 누락이 발생한 경우)
     * 이는 고객만이 상태 전이를 시킬 수 있다. (고객이 제품을 확인하기 때문)
     *  - 고객이 플랫폼 또는 기사에게 고지하는 경우: 기사/업체 누락으로 전이 (t16,t17)
     *  - 고객이 업체에게 고지하는 경우: 업체가 알아서 해결 or 업체가 플랫폼/기사 에게 고지 -> 기사/업체 누락으로 전이 (t16,t17)
     */
    DELIVERY_SUCCESS,

    /**
     * 기사 누락
     *
     * 제품이 누락되고 누락 사유가 기사의 실수인 경우이다.
     * 기사가 App 을 통해 누락 등록을 하여 기사 누락을 선택하면 해당 상태로 전이된다. (t13/t16)
     * 동시에 고객은 App 내에서 누락에 대해 notification(알림)을 받게되고,
     * 누락된 제품의 종류와 누락에 대한 보상을 선택하게 된다.
     * 제품 전체가 누락 된 경우 전체누락 처리가 되고, 제품 중 일부가 누락 된 경우 부분누락
     * 처리가 진행된다.
     * 고객은 다음 선택지를 받게 된다.
     *  - 누락제품의 금액에 대한 환불처리 + 할인 쿠폰 -> (t22)
     *  - 재배달 (퀵서비스) + 할인 쿠폰	-> (t18)
     * 기사 누락에 대한 환불금액 또는 퀵서비스 요금은 업체 정산에 포함되지 않는다.
     */
    MISS_BY_DELIVERER,

    /**
     * 업체 누락
     *
     * 제품이 누락되고 누락 사유가 업체의 실수인 경우이다.
     * 기사가 App 을 통해 누락 등록을 하여 업체 누락을 선택하면 해당 상태로 전이된다. (t14/t17)
     * 동시에 고객은 App 내에서 누락에 대해 notification(알림)을 받게되고,
     * 누락된 제품의 종류와 누락에 대한 보상을 선택하게 된다.
     * 제품 전체가 누락 된 경우 전체누락 처리가 되고, 제품 중 일부가 누락 된 경우 부분누락
     * 처리가 진행된다.
     * 고객은 다음 선택지를 받게 된다.
     *  - 누락제품의 금액에 대한 환불처리 + 할인 쿠폰 -> (t21)
     *  - 재배달 (퀵서비스) + 할인 쿠폰	-> (t19)
     * 업체 누락에 대한 환불금액 또는 퀵서비스 요금은 업체 정산에 포함된다.
     */
    MISS_BY_STORE,

    /**
     * 재 배달
     *
     * 제품이 누락되어 고객이 다시 배달 받기를 희망할 경우이다.
     * 고객이 App 을 통해 누락에 대해 재 배달을 선택한 경우, 해당 상태로 전이된다. (t18/t19)
     * 재 배달 상태를 서버에 기록하고 주문 퀵 대기 상태로 전이가 진행된다. (t20)
     * 퀵 서비스를 이용하여 재 배달되므로 고객은 배달 받을 상세 주소를 추가 기입해야 한다.
     */
    RE_DELIVERY
}
