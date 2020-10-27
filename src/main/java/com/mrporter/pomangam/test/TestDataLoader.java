package com.mrporter.pomangam.test;

import com.mrporter.pomangam.client.domains.deliverysite.DeliveryType;
import com.mrporter.pomangam.client.domains.faq.Faq;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.product.ProductType;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubType;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.test.data.advertisement.AdvertisementData;
import com.mrporter.pomangam.test.data.coupon.CouponData;
import com.mrporter.pomangam.test.data.deliverysite.DeliverySiteData;
import com.mrporter.pomangam.test.data.detailsite.DeliveryDetailSiteData;
import com.mrporter.pomangam.test.data.event.EventData;
import com.mrporter.pomangam.test.data.faq.FaqCategoryData;
import com.mrporter.pomangam.test.data.fcmtoken.FcmTokenData;
import com.mrporter.pomangam.test.data.notice.NoticeData;
import com.mrporter.pomangam.test.data.order.OrderData;
import com.mrporter.pomangam.test.data.ordertime.OrderTimeData;
import com.mrporter.pomangam.test.data.ordertimeMapper.OrderTimeMapperData;
import com.mrporter.pomangam.test.data.point.PointLogData;
import com.mrporter.pomangam.test.data.point.PointRankData;
import com.mrporter.pomangam.test.data.product.ProductData;
import com.mrporter.pomangam.test.data.productReply.ProductReplyData;
import com.mrporter.pomangam.test.data.productSub.ProductSubData;
import com.mrporter.pomangam.test.data.productSubCategory.ProductSubCategoryData;
import com.mrporter.pomangam.test.data.productSubMapper.ProductSubMapperData;
import com.mrporter.pomangam.test.data.randomNickname.RandomNicknameData;
import com.mrporter.pomangam.test.data.region.RegionData;
import com.mrporter.pomangam.test.data.staff.StaffData;
import com.mrporter.pomangam.test.data.store.StoreData;
import com.mrporter.pomangam.test.data.storeCategory.StoreCategoryData;
import com.mrporter.pomangam.test.data.storeOwner.StoreOwnerData;
import com.mrporter.pomangam.test.data.storeOwner.StoreOwnerTokenData;
import com.mrporter.pomangam.test.data.storeReview.StoreReviewData;
import com.mrporter.pomangam.test.data.storeReviewReply.StoreReviewReplyData;
import com.mrporter.pomangam.test.data.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Component
public class TestDataLoader implements ApplicationRunner {

    @Autowired RegionData region;
    @Autowired DeliverySiteData deliverySite;
    @Autowired AdvertisementData advertisement;
    @Autowired EventData event;
    @Autowired DeliveryDetailSiteData deliveryDetailSite;
    @Autowired FcmTokenData fcmToken;
    @Autowired PointRankData pointRank;
    @Autowired UserData userData;
    @Autowired StoreCategoryData storeCategory;
    @Autowired ProductSubCategoryData productSubCategory;
    @Autowired StoreData store;
    @Autowired ProductData product;
    @Autowired ProductSubData productSub;
    @Autowired ProductSubMapperData productSubMapper;
    @Autowired OrderTimeData orderTime;
    @Autowired OrderTimeMapperData orderTimeMapper;
    @Autowired CouponData coupon;
    // @Autowired PaymentData payment;
    @Autowired OrderData order;
    @Autowired RandomNicknameData randomNickname;
    @Autowired ProductReplyData productReply;
    @Autowired PointLogData pointLog;
    @Autowired NoticeData notice;
    @Autowired StoreOwnerData storeOwner;
    @Autowired StoreReviewData storeReviewData;
    @Autowired StoreReviewReplyData storeReviewReplyData;
    @Autowired StaffData staffData;
    @Autowired StoreOwnerTokenData storeOwnerToken;
    @Autowired FaqCategoryData faqCategory;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(ApplicationArguments args) {
        if( ddl.equals("create")) {
            run();
        }
    }

    @Transactional
    void run() {

        /*
         * 지역
         */
        region.of(1L, "경기");
        region.of(2L, "강원");
        region.of(3L, "제주");


        /*
         * 배달지
         */
        deliverySite.of(1L, "한국항공대", DeliveryType.BUNDLE,1L, "본캠", "경기도 고양시 덕양구 항공대학로 76");
        deliverySite.of(2L, "연세대", DeliveryType.BUNDLE,1L, "미래캠", "강원도 원주시 연세대길 1");
        deliverySite.of(3L, "탄현 풍림아파트 14단지", DeliveryType.BUNDLE,1L, "14단지", "경기도 고양시 탄현로 143-32");
        deliverySite.of(4L, "탄현 풍림아파트 15단지", DeliveryType.BUNDLE,1L, "15단지", "경기도 고양시 탄현로 143-32");


        /*
         * 배달지 상세 장소
         */
        deliveryDetailSite.of(1L, 1L, "학생회관 뒤", "학생회관 뒤 족구장 있는 곳",
                "ㅎ",1, 37.600326, 126.864485, 0);
        deliveryDetailSite.of(2L, 1L, "기숙사 식당", "기숙사 내부 식당",
                "ㄱ",2, 37.598048, 126.866489, 5);
        deliveryDetailSite.of(3L, 2L, "별관", "별관 식당",
                "ㄱ",1, 37.598048, 126.866489, 0);
        deliveryDetailSite.of(4L, 2L, "미디어관", "미디어 플레이스",
                "ㄴ",2, 37.598048, 126.866489, 7);

        deliveryDetailSite.of(5L, 3L, "101동", "101동",
                "101",1, 37.598048, 126.866489, 0);
        deliveryDetailSite.of(6L, 3L, "102동", "102동",
                "102",2, 37.598048, 126.866489, 10);
        deliveryDetailSite.of(7L, 3L, "103동", "103동",
                "103",3, 37.598048, 126.866489, 20);
        deliveryDetailSite.of(8L, 4L, "104동", "104동",
                "104",1, 37.598048, 126.866489, 0);
        deliveryDetailSite.of(9L, 4L, "105동", "105동",
                "105",2, 37.598048, 126.866489, 10);
        deliveryDetailSite.of(10L, 4L, "106동", "106동",
                "106",3, 37.598048, 126.866489, 20);


        /*
         * 광고
         */
        advertisement.of(1L, 1L, null, 1);
        advertisement.of(2L, 1L, null, 2);
        advertisement.of(3L, 1L, null, 3);
//        advertisement.of(4L, 1L, null, 4);
//        advertisement.of(5L, 1L, null, 5);

//        advertisement.of(6L, 3L, null, 1);
//        advertisement.of(7L, 3L, null, 2);
//        advertisement.of(8L, 3L, null, 3);
//        advertisement.of(9L, 3L, null, 4);
//        advertisement.of(10L, 3L, null, 5);
//
//        advertisement.of(11L, 4L, null, 1);
//        advertisement.of(12L, 4L, null, 2);
//        advertisement.of(13L, 4L, null, 3);
//        advertisement.of(14L, 4L, null, 4);
//        advertisement.of(15L, 4L, null, 5);

        /*
         * 이벤트
         */
        event.of(1L, 1L, "1일 1닭 이벤트", "1닭 을 받기 위해서는 블라블라",
                LocalDateTime.parse("2020-07-01T00:00:00"), null);
        event.of(2L, 1L, "쿠폰 이벤트", "쿠폰 을 받기 위해서는 블라블라",
                LocalDateTime.parse("2020-07-01T00:00:00"), LocalDateTime.parse("2020-08-01T00:00:00"));

        /*
         * 공지사항
         */
        notice.of(1L, 1L, "개인정보 처리방침 변경 안내", "안녕하세요. 대한민국 1등 반찬 정기배송 앱 포만감입니다.\n포만감 개인정보 처리방침이 아래와 같이 변경됩니다.\n\n\n1. 변경 사항",
                LocalDateTime.parse("2020-07-01T00:00:00"), null);
        notice.of(2L, 1L, "친구 초대 서비스 종료 안내", "친구 초대 서비스 종료 안내 블라블라",
                LocalDateTime.parse("2020-07-01T00:00:00"), null);

        /*
         * fcm 토큰
         */
        fcmToken.of(1L, "__FCM_TOKEN_1__");
        fcmToken.of(2L, "__FCM_TOKEN_2__");
        fcmToken.of(3L, "__FCM_TOKEN_3__");
        fcmToken.of(4L, "__FCM_TOKEN_4__");


        /*
         * 포인트 계급
         */
        pointRank.of(1L, "평범한", 1, 0.1F, 0, 500, 0, 0);
        pointRank.of(2L, "알뜰한", 2, 0.2F, 0, 1000, 10, 1);
        pointRank.of(3L, "살뜰한", 3, 0.3F, 0, 2000, 20, 10);
        pointRank.of(4L, "꾸준한", 4, 0.4F, 0, 3000, 40, 20);
        pointRank.of(5L, "현명한", 5, 0.5F, 0, 10000, 60, 30);
        pointRank.of(6L, "통달한", 6, 0.6F, 0, 20000, 80, 40);
        pointRank.of(7L, "포만한", 7, 0.7F, 0, 40000, 100, 50);


        /*
         * 유저
         */
        userData.of(1L, 1L,
                "01064784899", "1234", "최낙형", "낙지", Sex.MALE, LocalDate.parse("1993-01-10"),
                "ROLE_USER, ROLE_ADMIN");
        userData.of(2L, 2L,
                "01011111111", "1234", "최은성", "은스타", Sex.MALE, LocalDate.parse("1993-01-10"),
                "ROLE_STAFF");
        userData.of(3L, 1L,
                "01022222222", "1234", "김영찬", "찬찬", Sex.MALE, LocalDate.parse("1993-01-10"),
                "ROLE_USER");
        userData.of(4L, 1L,
                "01033333333", "1234", "윤태인", "윤탱", Sex.MALE, LocalDate.parse("1993-01-10"),
                "ROLE_USER");
        userData.of(5L, 3L,
                "01044444444", "1234", "김태희", "태희", Sex.FEMALE, LocalDate.parse("1993-01-10"),
                "ROLE_USER");


        /*
         * 업체 카테고리
         */
        storeCategory.of(1L, "한식");
        storeCategory.of(2L, "양식");
        storeCategory.of(3L, "중식");


        /*
         * 업체
         */
        store.of(1L, 1L, 2L, "맘스터치(항공대)", "엄마의 손맛, 수제햄버거 전문점 맘스터치", null,
                3.7F, 178, 54, 1, 1,
                Arrays.asList(1,2,3),
                Arrays.asList("고객리뷰", "이벤트안내"),
                Arrays.asList("세트", "단품"));
        store.of(2L, 1L, 1L, "한솥도시락(항공대)", "\uD83C\uDF71 싼맛! 싼마이 도시락! 한솥도시락 \uD83C\uDF71", "리뷰이벤트 중입니다 ♥",
                4.2F, 132, 79, 0, 2,
                Arrays.asList(1,2,3),
                Arrays.asList("new Arrival", "도시락증정"),
                Arrays.asList("보울도시락", "사각도시락", "프리미엄", "간식"));
        store.of(3L, 2L, 2L, "맘스터치(연세대)", "엄마의 손맛, 수제햄버거 전문점 맘스터치 -연세점-", null,
                4.6F, 209, 57, 0, 1,
                Arrays.asList(1,2,3),
                Arrays.asList("연세이벤트"),
                Arrays.asList("세트", "단품"));
        store.of(4L, 2L, 1L, "한솥도시락(연세대)", "\uD83C\uDF71 싼맛! 싼마이 도시락! 한솥도시락 \uD83C\uDF71", "항상 감사합니다.",
                4.0F, 343, 61, 0, 2,
                Arrays.asList(1,2,3),
                Arrays.asList("new Arrival", "도시락증정"),
                Arrays.asList("보울도시락", "사각도시락", "프리미엄", "간식"));
        store.of(5L, 1L, 2L, "피자매니", "껍질치밥으로 유명한 피자매니란다 \uD83C\uDF55\uD83C\uDF5F\uD83C\uDF2E", "리뷰이벤트 중입니다 \uD83E\uDD27\uD83E\uDD2D",
                2.7F, 32, 129, 0, 3,
                Arrays.asList(1,2,3),
                Arrays.asList("항공대이벤트", "1일 1닭"),
                Arrays.asList("피자", "치킨", "껍질", "간식"));
        store.of(6L, 1L, 3L, "항공반점", "항슐랭 \uD83C\uDF08 맛집 인증. 항공반점입니다.", null,
                4.4F, 56, 43, 0, 4,
                Arrays.asList(1,2,3),
                Arrays.asList(),
                Arrays.asList("메인", "서브", "프리미엄"));
        store.of(7L, 1L, 1L, "포만감 도시락", "\uD83D\uDE0E내가 먹고싶은 음식만 골라 담아 \uD83C\uDF71 도시락을 만들어 먹는다. \uD83D\uDE0B신개념 커스터마이징 도시락\uD83D\uDC40", "\uD83D\uDD25항공대 이벤트 중입니다.\uD83D\uDD25",
                4.9F, 627, 311, 0, 3,
                Arrays.asList(1,2,3),
                Arrays.asList("고객리뷰", "이벤트안내"),
                Arrays.asList("메인 도시락", "서브 도시락", "프리미엄 도시락"));

        store.of(8L, 3L, 1L, "반찬탁", "\uD83C\uDF71 싼맛! 싼마이 도시락! 한솥도시락 \uD83C\uDF71 \uD83D\uDE0E", "\uD83D\uDD25탄현 이벤트 중입니다.\uD83D\uDD25",
                4.3F, 4687, 988, 0, 1,
                Arrays.asList(1,2,3),
                Arrays.asList("고객리뷰", "이벤트안내"),
                Arrays.asList("메인 도시락", "서브 도시락", "프리미엄 도시락"));

        store.of(9L, 3L, 1L, "포만감 도시락", "\uD83D\uDE0E내가 먹고싶은 음식만 골라 담아 \uD83C\uDF71 도시락을 만들어 먹는다. \uD83D\uDE0B신개념 커스터마이징 도시락\uD83D\uDC40", "\uD83D\uDD25탄현 이벤트 중입니다.\uD83D\uDD25",
                4.9F, 627, 311, 0, 2,
                Arrays.asList(1,2,3),
                Arrays.asList("고객리뷰", "이벤트안내"),
                Arrays.asList("메인 도시락", "서브 도시락", "프리미엄 도시락"));

        /*
         * 제품
         */
        product.of(1L,"싸이버거 세트", ProductType.NORMAL,
                1L, 1L, 1, 6_000, 1_000, 500,
                1, 2, 3);
        product.of(2L,"싸이버거", ProductType.NORMAL,
                1L, 2L, 2, 4_000, 1_000, 500,
                1, 2, 3);
        product.of(3L,"휠렛버거 세트", ProductType.NORMAL,
                1L, 1L, 3, 5_500, 1_000, 500,
                1, 2, 3);
        product.of(4L,"휠렛버거", ProductType.NORMAL,
                1L, 2L, 4, 3_500, 1_000, 500,
                1, 2, 3);

        product.of(5L,"갈비치킨마요", ProductType.NORMAL,
                2L, 3L, 1, 3_200, 1_000, 300,
                1,2,3);
        product.of(6L,"메가치킨마요", ProductType.NORMAL,
                2L, 3L, 2, 5_500, 1_000, 300,
                1,2,3);
        product.of(7L,"메가치킨제육", ProductType.NORMAL,
                2L, 4L, 3, 6_900, 1_000, 300,
                1,2,3);
        product.of(8L,"치킨마요", ProductType.NORMAL,
                2L, 3L, 4, 2_900, 1_000, 300,
                1,2,3);
        product.of(9L,"빅치킨마요", ProductType.NORMAL,
                2L, 3L, 5, 3_500, 1_000, 300,
                1);
        product.of(10L,"숯불직화구이 덮밥", ProductType.NORMAL,
                2L, 5L, 6, 5_700, 1_000, 300,
                1);
        product.of(11L,"왕치킨마요", ProductType.NORMAL,
                2L, 3L, 7, 4_200, 1_000, 300,
                1);
        product.of(12L,"한솥 철판볶음밥", ProductType.NORMAL,
                2L, 4L, 8, 3_700, 1_000, 300,
                1);
        product.of(13L,"돈까스 카레", ProductType.NORMAL,
                2L, 3L, 9, 3_900, 1_000, 300,
                1);
        product.of(14L,"불닭비빔밥", ProductType.NORMAL,
                2L, 5L, 10, 4_500, 1_000, 300,
                1);
        product.of(15L,"돈치마요", ProductType.NORMAL,
                2L, 3L, 11, 3_500, 1_000, 300,
                1);

        product.of(16L,"도시락(S)", ProductType.CUSTOMIZING_3,
                7L, 20L, 1, 3_000, 1_000, 500,
                1);
        product.of(17L,"도시락(M)", ProductType.CUSTOMIZING_4,
                7L, 20L, 2, 4_500, 1_000, 500,
                1);
        product.of(18L,"도시락(L)", ProductType.CUSTOMIZING_5,
                7L, 20L, 3, 6_000, 1_000, 500,
                1);


        /*
         * 서브 카테고리
         */
        productSubCategory.of(1L, "맛 필수 선택", ProductSubType.RADIO);
        productSubCategory.of(2L, "서브", ProductSubType.CHECKBOX);
        productSubCategory.of(3L, "음료", ProductSubType.CHECKBOX);
        productSubCategory.of(4L, "서브", ProductSubType.CHECKBOX);
        productSubCategory.of(5L, "음료", ProductSubType.CHECKBOX);
        productSubCategory.of(6L, "선택1", ProductSubType.CUSTOMIZING_SUB);
        productSubCategory.of(7L, "선택2", ProductSubType.CUSTOMIZING_SUB);
        productSubCategory.of(8L, "선택3", ProductSubType.CUSTOMIZING_SUB);
        productSubCategory.of(9L, "선택4", ProductSubType.CUSTOMIZING_SUB);
        productSubCategory.of(10L, "선택5", ProductSubType.CUSTOMIZING_SUB);

        /*
         * 서브 제품
         */
        productSub.of(1L, 1L, 1L, 1L, "착한맛", null, null,1, 0, null, null, Arrays.asList());
        productSub.of(2L, 1L, 1L, 1L, "보통맛", null, null,2, 0, null, null, Arrays.asList());
        productSub.of(3L, 1L, 1L, 1L, "매운맛", null, null,3, 0, null, null, Arrays.asList());
        productSub.of(4L, 1L, 1L, 2L, "케이준양념감자", "쫀딕쫀딕 케이준감자에 양념 뭍힌것이여~", "쪼끔 맵땅깨~",1, 160, 0, 10, Arrays.asList(1,2));
        productSub.of(5L, 1L, 1L, 4L, "사이즈업", null, null,4, 1000, null, null, Arrays.asList());
        productSub.of(6L, 1L, 1L, 3L, "콜라", "코카콜라", "500ml",2, 1300, null, null, Arrays.asList(1));
        productSub.of(7L, 1L, 1L, 3L, "사이다", "칠성사이다", "500ml",3, 1300, null, null, Arrays.asList(1));
        productSub.of(8L, 1L, 1L, 5L, "콜라375", "코카콜라375", "375ml",5, 1000, null, null, Arrays.asList(1));
        productSub.of(9L, 1L, 1L, 5L, "사이다375", "칠성사이다375", "375ml",6, 1000, null, null, Arrays.asList(1));

        productSub.of(10L, 1L, 7L, 6L, "흰쌀밥", null, null,1, 0, null, null, Arrays.asList(1));
        productSub.of(11L, 1L, 7L, 6L, "현미밥", "국내산 현미 100%", null,2, 0, null, null, Arrays.asList(1));
        productSub.of(12L, 1L, 7L, 6L, "잡곡밥", "국내산 잡곡 100%", null,3, 0, null, null, Arrays.asList(1));
        productSub.of(13L, 1L, 7L, 6L, "서리태밥", "국내산 서리태 100%", null,4, 300, null, null, Arrays.asList(1));
        productSub.of(14L, 1L, 7L, 6L, "김치베이컨 필라프", "김치와 베이컨을 볶은 필라프", null,5, 1000, null, null, Arrays.asList(1));

        productSub.of(15L, 1L, 7L, 7L, "제육볶음", "국내산 돼지고기", "650g",1, 0, null, null, Arrays.asList(1));
        productSub.of(16L, 1L, 7L, 7L, "제육볶음 곱빼기", "국내산 돼지고기", "850g",2, 1000, null, null, Arrays.asList(1));
        productSub.of(17L, 1L, 7L, 7L, "돈까스", "수제 돈까스", "500g",3, 0, null, null, Arrays.asList(1));
        productSub.of(18L, 1L, 7L, 7L, "치즈돈까스", "임실치즈 수제 돈까스", "600g",4, 500, null, null, Arrays.asList(1));
        productSub.of(19L, 1L, 7L, 7L, "고등어조림", "국내산 고등어 조림", "고등어 2개",5, 0, null, null, Arrays.asList(1));
        productSub.of(20L, 1L, 7L, 7L, "고기산적", "다진고기로 만든 산적", "산적 3개",6, 0, null, null, Arrays.asList(1));

        productSub.of(21L, 1L, 7L, 8L, "김치", "국내산 김치", "250g",1, 0, null, null, Arrays.asList(1));
        productSub.of(22L, 1L, 7L, 8L, "볶음김치", "국내산 김치", "250g",2, 0, null, null, Arrays.asList(1));
        productSub.of(23L, 1L, 7L, 8L, "멸치볶음", "국내산 멸치볶음", "150g",3, 0, null, null, Arrays.asList(1));
        productSub.of(24L, 1L, 7L, 8L, "연근조림", "싱싱한 연근조림", "200g",4, 0, null, null, Arrays.asList(1));
        productSub.of(25L, 1L, 7L, 8L, "무말랭이", "국내산 무말랭이 무침", "300g",5, 0, null, null, Arrays.asList(1));
        productSub.of(26L, 1L, 7L, 8L, "봄나물무침", "향긋한 봄나물", "200g",6, 0, null, null, Arrays.asList(1));

        productSub.of(27L, 1L, 7L, 9L, "간장게장", "국내산 암게", "200g",1, 1000, null, null, Arrays.asList(1));
        productSub.of(28L, 1L, 7L, 9L, "양념게장", "국내산 암게", "200g",2, 1000, null, null, Arrays.asList(1));

        productSub.of(29L, 1L, 7L, 10L, "소세지볶음", null, "150g",1, 0, null, null, Arrays.asList(1));
        productSub.of(30L, 1L, 7L, 10L, "미트볼", "부드러운 미트볼", "150g",2, 0, null, null, Arrays.asList(1));
        productSub.of(31L, 1L, 7L, 10L, "미니돈까스", "수제 돈까스", "150g",3, 0, null, null, Arrays.asList(1));
        productSub.of(32L, 1L, 7L, 10L, "새우튀김", "30미 새우", "새우 2개",4, 0, null, null, Arrays.asList(1));
        productSub.of(33L, 1L, 7L, 10L, "계란후라이", null, null,5, 0, null, null, Arrays.asList(1));


        /*
         * 제품-서브 연결
         */
        productSubMapper.of(1L, 4L, 6L, 7L);
        productSubMapper.of(2L, 4L, 6L, 7L);
        productSubMapper.of(3L, 4L, 6L, 7L);
        productSubMapper.of(4L, 4L, 6L, 7L);
        productSubMapper.of(5L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(6L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(7L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(8L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(9L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(10L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(11L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(12L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(13L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(14L, 1L, 2L, 3L, 5L, 8L, 9L);
        productSubMapper.of(15L, 1L, 2L, 3L, 5L, 8L, 9L);

        productSubMapper.of(16L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L);
        productSubMapper.of(17L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L);
        productSubMapper.of(18L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L);


        /*
         * 시간표
         */
        // 항공대 1
        orderTime.of(1L, LocalTime.parse("11:30:00"), LocalTime.parse("11:45:00"), LocalTime.parse("12:00:00"));
        orderTime.of(2L, LocalTime.parse("12:30:00"), LocalTime.parse("12:45:00"), LocalTime.parse("13:00:00"));
        orderTime.of(3L, LocalTime.parse("17:30:00"), LocalTime.parse("17:45:00"), LocalTime.parse("18:00:00"));
        orderTime.of(4L, LocalTime.parse("18:30:00"), LocalTime.parse("18:45:00"), LocalTime.parse("19:00:00"));
        // 항공대 2
        orderTime.of(5L, LocalTime.parse("12:00:00"), LocalTime.parse("12:15:00"), LocalTime.parse("12:30:00"));
        orderTime.of(6L, LocalTime.parse("13:00:00"), LocalTime.parse("13:15:00"), LocalTime.parse("13:30:00"));
        orderTime.of(7L, LocalTime.parse("18:00:00"), LocalTime.parse("18:15:00"), LocalTime.parse("18:30:00"));
        orderTime.of(8L, LocalTime.parse("19:00:00"), LocalTime.parse("19:15:00"), LocalTime.parse("19:30:00"));
        // 연세대 미래캠 1
        orderTime.of(9L, LocalTime.parse("11:30:00"), LocalTime.parse("11:45:00"), LocalTime.parse("12:10:00"));
        orderTime.of(10L, LocalTime.parse("12:30:00"), LocalTime.parse("12:45:00"), LocalTime.parse("13:10:00"));
        orderTime.of(11L, LocalTime.parse("17:30:00"), LocalTime.parse("17:45:00"), LocalTime.parse("18:10:00"));
        orderTime.of(12L, LocalTime.parse("18:30:00"), LocalTime.parse("18:45:00"), LocalTime.parse("19:10:00"));

        /// 탄현
        orderTime.of(13L, LocalTime.parse("06:30:00"), LocalTime.parse("06:45:00"), LocalTime.parse("07:00:00"));
        orderTime.of(14L, LocalTime.parse("11:30:00"), LocalTime.parse("11:45:00"), LocalTime.parse("12:00:00"));
        orderTime.of(15L, LocalTime.parse("17:30:00"), LocalTime.parse("17:45:00"), LocalTime.parse("18:00:00"));


        /*
         * 시간표 연결
         */
        orderTimeMapper.of(1L, 1L, 2L, 3L, 4L);     // 항공대 맘스터치
        orderTimeMapper.of(2L, 1L, 2L, 3L, 4L);     // 항공대 한솥도시락
        orderTimeMapper.of(3L, 9L, 10L, 11L, 12L);  // 연세대 미래캠 맘스터치
        orderTimeMapper.of(4L, 9L, 10L, 11L, 12L);  // 연세대 미래캠 한솥도시락
        orderTimeMapper.of(5L, 5L, 6L, 7L, 8L);     // 항공대 피자매니
        orderTimeMapper.of(6L, 5L, 6L, 7L, 8L);     // 항공대 항공반점
        orderTimeMapper.of(7L, 1L, 2L, 3L, 4L);     // 항공대 포만감 도시락

        orderTimeMapper.of(8L, 13L, 14L, 15L);     // 탄현 반찬탁
        orderTimeMapper.of(9L, 13L, 14L, 15L);     // 탄현 포만감 도시락

        /*
         * 쿠폰
         */
        coupon.of(1L, 1L, "1,000원 할인쿠폰", "1XER-FGT3-1199", LocalDateTime.now(), null, 1_000, false);
        coupon.of(2L, 1L, "2,000원 할인쿠폰", "235D-FE34-3300", LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), 2_000, false);
        coupon.of(3L, 1L, "5,000원 할인쿠폰", "45DD-6GGG-4566", LocalDateTime.now(), null, 5_000, false);
        coupon.of(4L, 1L, "1,500원 할인쿠폰", "4JU6-YU55-TTHH", LocalDateTime.now(), null, 1_500, false);
        coupon.of(5L, 1L, "2,000원 할인쿠폰", "25HS-FE34-905H", LocalDateTime.now(), null, 2_000, true);
        coupon.of(6L, 1L, "2,000원 할인쿠폰", "98JN-FEEE-3540", LocalDateTime.now(), null, 2_000, true);

        coupon.of(7L, null, "2,500원 할인쿠폰", "TEST-TEST-1111", LocalDateTime.now(), null, 2_500, false);
        coupon.of(8L, null, "2,500원 할인쿠폰", "TEST-TEST-2222", LocalDateTime.now(), null, 2_500, false);


        /*
         * 결제수단
         */
//        payment.of(1L, PaymentType.CREDIT_CARD);
//        payment.of(2L, PaymentType.PHONE);
//        payment.of(3L, PaymentType.V_BANK);


        /*
         * 주문
         */
        order.of(1L, 1, 1L, LocalDate.now(), 1L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 2, 1L);
        order.of(2L, 2, 1L, LocalDate.now(), 1L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 2);
        order.of(3L, 3, 1L, LocalDate.now(), 2L, PaymentType.PERIODIC_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 1);
        order.of(4L, 4, 1L, LocalDate.now(), 1L, PaymentType.PERIODIC_CREDIT_CARD, OrderType.DELIVERY_READY,
                1L, 1L, 1);
        order.of(5L, 5, 1L, LocalDate.now(), 1L, PaymentType.PERIODIC_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 2);
        order.of(6L, 6, 1L, LocalDate.now(), 2L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 1);
        order.of(7L, 7, 1L, LocalDate.now(), 1L, PaymentType.COMMON_CREDIT_CARD, OrderType.DELIVERY_READY,
                1L, 1L, 1);
        order.of(8L, 8, 1L, LocalDate.now(), 10L, PaymentType.COMMON_KAKAOPAY, OrderType.ORDER_READY,
                3L, 1L, 2);
        order.of(9L, 9, 1L, LocalDate.now(), 10L, PaymentType.COMMON_KAKAOPAY, OrderType.DELIVERY_READY,
                4L, 1L, 1);
        order.of(10L, 10, 1L, LocalDate.now(), 10L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                3L, 1L, 1);
        order.of(11L, 11, 1L, LocalDate.now(), 11L, PaymentType.COMMON_CREDIT_CARD, OrderType.DELIVERY_READY,
                3L, 1L, 2);
        order.of(12L, 12, 1L, LocalDate.now(), 11L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                4L, 1L, 1);
        order.of(13L, 13, 1L, LocalDate.now(), 10L, PaymentType.COMMON_V_BANK, OrderType.PAYMENT_READY,
                3L, 1L, 1);


        /*
         * 제품 댓글
         */
        productReply.of(1L, 1L, "몇일 전 부터 [싸이버거세트]가 너무 먹고 싶어서 시켜 먹었는데 맛뿐만 아니라 양이 너무 많아서 깜짝놀랄 정도 였습니다. 두 번 먹으세요!!", false);
        productReply.of(2L, 1L, "아니, 이딴걸 왜 돈주고 사먹는지 이해가 안갈정도임ㅡㅡ", true);
        productReply.of(2L, 1L, "흠 별로내요.. 학기중에 절대로 다시는 시켜먹고 싶지 않네요.", true);
        productReply.of(1L, 5L, "몇일 전 부터 [갈비치킨마요]가 너무 먹고 싶어서 시켜 먹었는데 맛뿐만 아니라 양이 너무 많아서 깜짝놀랄 정도 였습니다. 두 번 먹으세요!!", false);
        productReply.of(2L, 5L, "아니, 이딴걸 왜 돈주고 사먹는지 이해가 안갈정도임ㅡㅡ", true);
        productReply.of(2L, 5L, "흠 별로내요.. 학기중에 절대로 다시는 시켜먹고 싶지 않네요.", true);


        /*
         * 포인트 로그
         */
        pointLog.plus(1L, 1000, PointType.ISSUED_BY_BUY, 1L);
        pointLog.plus(1L, 500, PointType.ISSUED_BY_BUY, 2L);
        pointLog.plus(1L, 300, PointType.ISSUED_BY_BUY, 3L);
        pointLog.plus(1L, 2000, PointType.ISSUED_BY_PROMOTION, null);
        pointLog.minus(1L, 3000, PointType.USED_BY_BUY, 4L);

        pointLog.plus(2L, 300, PointType.ISSUED_BY_BUY, 5L);
        pointLog.plus(2L, 5000, PointType.UPDATED_PLUS_BY_ADMIN, null);
        pointLog.plus(2L, 300, PointType.ISSUED_BY_BUY, 7L);


        /*
         * 업주
         */
        storeOwner.of(1L, 1L, 1L, "store_1", "store_1_pw", "업체1", "010-6478-4899", Sex.MALE, LocalDate.now(), null);
        storeOwner.of(2L, 2L, 2L, "store_2", "store_2_pw", "업체2", "010-6478-4899", Sex.FEMALE, LocalDate.now(), null);
        storeOwner.of(3L, 3L, 3L, "store_3", "store_3_pw", "업체3", "010-6478-4899", Sex.MALE, LocalDate.now(), null);

        storeOwnerToken.of(1L, "store_1", "cWSA3jZnTMWVayi9-mvZXn:APA91bGaPmklIy7V6lfyRX0ssvehBxrMptSmMAQ9Uf-olInaEJfGN0e88fMsQUEiAn3sl3ibohvoklgNyGCfN4M8UTf_MsXWXOklN-acwj8I2NAbaa8JrVc0yosbg1gUCjACPM85MVXP");
        storeOwnerToken.of(2L, "store_2", "zz");
        storeOwnerToken.of(3L, "store_3", "cWSA3jZnTMWVayi9-mvZXn:APA91bGaPmklIy7V6lfyRX0ssvehBxrMptSmMAQ9Uf-olInaEJfGN0e88fMsQUEiAn3sl3ibohvoklgNyGCfN4M8UTf_MsXWXOklN-acwj8I2NAbaa8JrVc0yosbg1gUCjACPM85MVXP");

        /*
         * 관리자
         */
        staffData.of(1L, 41L, "staff_1", "staff_1_pw", "직원1", "010-6478-4899", Sex.MALE, LocalDate.now(), null);
        staffData.of(2L, 42L, "staff_2", "staff_2_pw", "직원2", "010-6478-4899", Sex.FEMALE, LocalDate.now(), null);
        staffData.of(3L, 43L, "staff_3", "staff_3_pw", "직원3", "010-6478-4899", Sex.FEMALE, LocalDate.now(), null);
        staffData.of(4L, 44L, "staff_4", "staff_4_pw", "직원4", "010-6478-4899", Sex.MALE, LocalDate.now(), null);
        staffData.of(5L, 45L, "admin", "1234", "관리자", "010-6478-4899", Sex.MALE, LocalDate.now(), "ROLE_ADMIN");


        /*
         * 업체 리뷰
         */
        storeReviewData.of(1L, 1L, 1L, 1L, "이 업체 너무 좋아요", "내용입니다~~", 4.5F, "싸이버거 세트",
                "리뷰 감사드립니다~~ 쿠폰 증정해드렸어용 ㅎㅎ", LocalDateTime.now(),
                false, "1", "2", "3", "4", "5");
        storeReviewData.of(2L, 2L, 1L, 1L, "여기 음식이 전반적으로 짜네요.", "조금만 더 싱거웠으면..", 3.0F, "싸이버거 단품",
                "감사합니다^^", LocalDateTime.now(),
                true, "1", "2", "3");
        storeReviewData.of(3L, 3L, 1L, 1L, "조금 짜네요.", "밥도둑 ㅎㅎㅎ..", 2.5F, "제품이름~~",
                "사장 댓글이에용~", LocalDateTime.now(),
                false);


        /*
         * 업체 리뷰 - 댓글
         */
        storeReviewReplyData.of(4L, 1L, "저도 그렇게 생각합니다^^", false);
        storeReviewReplyData.of(4L, 2L, "저는 그렇게 생각하지않습니다ㅡㅡ", true);
        storeReviewReplyData.of(4L, 3L, "저도 그렇게 생각합니다^^**", false);


        /*
         * 자주 묻는 질문
         */
        Faq faq1 = Faq.builder().idx(1L)
                .title("음식을 주문하려면 어떻게 해야 하나요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq2 = Faq.builder().idx(2L)
                .title("정해진 시간에만 주문이 가능한가요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq3 = Faq.builder().idx(3L)
                .title("정해진 장소에서만 받을 수 있나요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq4 = Faq.builder().idx(4L)
                .title("결제 취소는 어떻게 하나요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq5 = Faq.builder().idx(5L)
                .title("결제 환불을 하고 싶어요.")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq6 = Faq.builder().idx(6L)
                .title("리뷰는 어떻게 작성하나요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq7 = Faq.builder().idx(7L)
                .title("포인트 사용은 어떻게 하나요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();
        Faq faq8 = Faq.builder().idx(8L)
                .title("음식에서 이물질이 나왔는데 어디에 연락해야 하나요?")
                .contents("자주 묻는 질문 <b>테스트</b> 입니다.🎈🎃").build();


        faqCategory.of(1L, 1L, "주문문의", faq1,faq2,faq3);
        faqCategory.of(2L, 1L, "결제문의", faq4,faq5);
        faqCategory.of(3L, 1L, "이용문의", faq6,faq7);
        faqCategory.of(4L, 1L, "기타", faq8);


        /*
         * 랜덤 닉네임
         */
//        String[] ff = {"배부른", "용감한", "갸냘픈", "가엾은", "굵은", "던지는", "마법사", "방금온", "브론즈", "마스터", "실버", "골드", "플레티넘", "완고한", "다이아", "감각적인", "가벼운", "잘생긴", "어여쁜"};
//        String[] ss = {"얼굴", "사마귀", "북극곰", "콜라", "아이폰", "향수", "꼬부기", "파이리", "롱스톤", "티모", "가렌", "마스터이", "언랭", "페이커", "포만이", "비타민", "발바닥", "손바닥", "지갑"};
//        randomNickname.of(ff, ss);
    }
}
