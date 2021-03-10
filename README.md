```
███╗   ███╗██████╗    ██████╗  ██████╗ ██████╗ ████████╗███████╗██████╗
████╗ ████║██╔══██╗   ██╔══██╗██╔═══██╗██╔══██╗╚══██╔══╝██╔════╝██╔══██╗
██╔████╔██║██████╔╝   ██████╔╝██║   ██║██████╔╝   ██║   █████╗  ██████╔╝
██║╚██╔╝██║██╔══██╗   ██╔═══╝ ██║   ██║██╔══██╗   ██║   ██╔══╝  ██╔══██╗
██║ ╚═╝ ██║██║  ██║██╗██║     ╚██████╔╝██║  ██║   ██║   ███████╗██║  ██║
╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝      ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝
```
---

## 예약배송 플랫폼 포만감v1.0~1.2, REST API 서버

### 수행기간
- 2018-11 ~ 2021-02

### 사용기술
- Java, Spring Boot2, Spring Security, Spring OAuth2.0, JPA, Ehcache, Grafana, Prometheus, NCP, Fcm, Lombok

### 인원
- 1명 ([NTEVE](https://github.com/cholnh))

### 개요
- Spring Boot로 제작된 REST API 서버. 앱과 웹에서 보내는 데이터 요청을 받아 처리하여 응답.

### 주요특징
|키워드|설명|링크|
|--|--|--|
| 공통 설계 | DB Layer, Service Layer, Controller Layer, CrossCutting Concerns Layer 로 나누어 SoC 개념 및 의존성 규칙 적용. |[:arrow_right:](https://github.com/cholnh/pomangam-api/tree/master/src/main/java/com/mrporter/pomangam/client)|
| Spring Data JPA | JPA의 CRUD, Page 등 사용하여 Repository 구성. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/master/src/main/java/com/mrporter/pomangam/client/repositories/deliverysite/DeliverySiteJpaRepository.java#L18) |
| Spring Data JPA | 1:N, N:M, Embeddable 등 사용하여 데이터 관계 구현. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/bbb10cdd6984d06b4e58d78651690c824a2a3bcf/src/main/java/com/mrporter/pomangam/client/domains/product/Product.java#L76-L83) |
| QueryDSL | 동적 쿼리 구현, Lazy Loading으로 인한 N+1 문제를 Fetch join을 통해 해결. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/master/src/main/java/com/mrporter/pomangam/client/repositories/deliverysite/DeliverySiteJpaRepository.java#L51) |
| Entity Audit | ID(기본키), 활성화여부, 등록날짜, 수정날짜 등 Audit 관련 속성들을 상속하여 사용할 수 있게 구현. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/master/src/main/java/com/mrporter/pomangam/client/domains/_bases/EntityAuditing.java) |
| Spring Transaction | 주문 처리중 외부/내부 오류 발생 시, 거래를 초기로 rollback하여 거래의 Atomicity을 유지. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/bbb10cdd6984d06b4e58d78651690c824a2a3bcf/src/main/java/com/mrporter/pomangam/client/services/order/OrderServiceImpl.java#L239-L249) |
| EhCache | 병목현상이 이뤄지는 곳에 캐시 적용. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/d880c5d5b6d52790762b9d71f9abb57069b8ca57/src/main/java/com/mrporter/pomangam/client/services/product/ProductServiceImpl.java#L38) |
| Spring Security | Pre/PostAuthorize를 통해 Principal 의 Role(Authority)을 검사하여 Authorization을 관리. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/d880c5d5b6d52790762b9d71f9abb57069b8ca57/src/main/java/com/mrporter/pomangam/admin/controllers/staff/StaffController.java#L15) |
| Spring OAuth | OAuth Authorization Server 및 Resource Server 구현. Grant-Type 중 Client Credential, Password 정보를 받아 Token 응답 및 Token 유효성 검사. |[:arrow_right:](https://github.com/cholnh/pomangam-api/tree/master/src/main/java/com/mrporter/pomangam/_bases/securities/config) |
| Spring Actuator | 서버 health 관리 및 모니터링. Prometheus Server를 통해 health 매트릭을 수집, Grafana를 통해 서버상태를 시각화하여 에러 모니터링 및 대응. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/master/src/main/java/com/mrporter/pomangam/_bases/securities/filter/grafana.md) |
| NCP | Naver Cloud Platform 서버 호스팅 서비스 사용. | |
| 번호 로그인 구현 | 문자메시지 API를 사용하여 인증모듈 구현, Spring Security 의 UserDetailService 를 implements 하여 인증모듈과 연동. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/master/src/main/java/com/mrporter/pomangam/_bases/securities/service/UserDetailsServiceImpl.java) |
| 카카오 로그인 구현 | Kakao API를 사용하여 간편로그인을 구현. |[:arrow_right:](https://github.com/cholnh/pomangam-api/blob/master/src/main/java/com/mrporter/pomangam/_bases/securities/kakaoauth/service/KakaoOauthServiceImpl.java) |

### 설계모델
|Server Architecture|
|--|
|![sys](https://user-images.githubusercontent.com/23611497/110599915-b8de0d80-81c6-11eb-9068-1048438c61b7.jpg)|

### 결과
- v1.2 API 서버 ( https://poman.kr:9530/api/v1.2 )

### 문서
- v1.0 API 문서 ( https://pomangam.docs.apiary.io )
- 주문 프로세스 문서 ( https://docs.google.com/document/d/1NTP_Ha8Gyu34hkUNxTii0bGyOfa5j-gOX-HamKXpCFw/edit )
