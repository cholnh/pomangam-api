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
import com.mrporter.pomangam.test.data.map.MapData;
import com.mrporter.pomangam.test.data.notice.NoticeData;
import com.mrporter.pomangam.test.data.order.OrderData;
import com.mrporter.pomangam.test.data.ordertime.OrderTimeData;
import com.mrporter.pomangam.test.data.ordertimeDsiteMapper.OrderTimeDeliverySiteMapperData;
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
import com.mrporter.pomangam.test.data.storeMapper.StoreMapperData;
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
public class BaseDataLoader implements ApplicationRunner {

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
    @Autowired MapData map;
    @Autowired StoreMapperData storeMapper;
    @Autowired OrderTimeDeliverySiteMapperData orderTimeDeliverySiteMapper;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(ApplicationArguments args) {
        if( ddl.equals("create")) {
            //run();
        }
    }

    @Transactional
    void run() {

        /*
         * common map
         */
        map.of("boolean_vbank_service_onoff", "true");

        /*
         * 지역
         */
        region.of(1L, "경기");


        /*
         * 배달지
         */
        deliverySite.of(1L, "한국항공대 본캠", DeliveryType.BUNDLE,1L, "본캠", "경기도 고양시 덕양구 항공대학로 76");


        /*
         * 배달지 상세 장소
         */
        deliveryDetailSite.of(1L, 1L, "학생회관 뒤", "학생회관 뒤 족구장 있는 곳",
                "ㅎ",1, 37.600326, 126.864485, 0);
        deliveryDetailSite.of(2L, 1L, "기숙사 식당", "기숙사 내부 식당",
                "ㄱ",2, 37.598048, 126.866489, 10);


        /*
         * 광고
         */
        advertisement.of(1L, 1L, null, 1);
        advertisement.of(2L, 1L, null, 2);
        advertisement.of(3L, 1L, null, 3);


        /*
         * 이벤트
         */


        /*
         * 공지사항
         */


        /*
         * fcm 토큰
         */


        /*
         * 포인트 계급
         */
        pointRank.of(1L, "브론즈", 1, 0.1F, 0, 500, 0, 0);
        pointRank.of(2L, "실버", 2, 0.2F, 0, 1000, 10, 1);
        pointRank.of(3L, "골드", 3, 0.3F, 0, 2000, 20, 10);
        pointRank.of(4L, "플레티넘", 4, 0.4F, 0, 3000, 40, 20);
        pointRank.of(5L, "다이아몬드", 5, 0.5F, 0, 10000, 60, 30);
        pointRank.of(6L, "마스터", 6, 0.6F, 0, 20000, 80, 40);
        pointRank.of(7L, "챌린저", 7, 0.7F, 0, 40000, 100, 50);


        /*
         * 유저
         */


        /*
         * 업체 카테고리
         */
        storeCategory.of(1L, "한식");
        storeCategory.of(2L, "양식");
        storeCategory.of(3L, "중식");


        /*
         * 업체
         */

        store.of(1L, 1L, 1L, "포만감 도시락", "🍱 내가 먹고싶은 음식만 골라 담는 가성비 커스터마이징 도시락 \uD83C\uDF71", null,
                0F, 0, 0, 0, 1,
                Arrays.asList(1,2,3),
                Arrays.asList("", ""),
                Arrays.asList("메인 도시락"));


        /*
         * 배달지 - 업체 연결
         */
        storeMapper.of(1L, 1L);


        /*
         * 제품
         */
        product.of(1L,"도시락(S)", ProductType.CUSTOMIZING_3,
                1L, 1L, 1, 4_000, 0, 0,
                1);
        product.of(2L,"도시락(M)", ProductType.CUSTOMIZING_4,
                1L, 1L, 2, 5_000, 0, 0,
                1);
        product.of(3L,"도시락(L)", ProductType.CUSTOMIZING_5,
                1L, 1L, 3, 6_000, 0, 0,
                1);


        /*
         * 서브 카테고리
         */
        productSubCategory.of(1L, "필수선택1", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(2L, "필수선택2", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(3L, "필수선택3", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(4L, "필수선택4", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(5L, "필수선택5", ProductSubType.CUSTOMIZING_SUB, true);

        productSubCategory.of(6L, "국", ProductSubType.CUSTOMIZING_SUB, false);
        productSubCategory.of(7L, "음료", ProductSubType.CUSTOMIZING_SUB, false);


        /*
         * 서브 제품
         */
        productSub.of(1L, 1L, 1L, 7L, "콜라", "코카콜라", "500ml",1, 1000, null, null, Arrays.asList(1));
        productSub.of(2L, 1L, 1L, 7L, "사이다", "칠성사이다", "500ml",2, 1000, null, null, Arrays.asList(1));

        productSub.of(3L, 1L, 1L, 6L, "김치찌개", "", "",1, 3000, null, null, Arrays.asList(1));

        // 3찬
        productSub.of(4L, 1L, 1L, 1L, "흰쌀밥", null, "250g",1, 0, null, null, Arrays.asList(1));
        productSub.of(5L, 1L, 1L, 1L, "볶음밥", null, "250g",2, 0, null, null, Arrays.asList(1));
        productSub.of(6L, 1L, 1L, 1L, "제육볶음", null, "200g",3, 0, null, null, Arrays.asList(1));
        productSub.of(7L, 1L, 1L, 1L, "돼지불백", null, "190g",4, 0, null, null, Arrays.asList(1));

        productSub.of(6L, 1L, 1L, 2L, "무생채", null, "60g",1, 0, null, null, Arrays.asList(1));
        productSub.of(7L, 1L, 1L, 2L, "감자볶음", null, "45g",2, 0, null, null, Arrays.asList(1));
        productSub.of(8L, 1L, 1L, 2L, "애호박", null, "60g",3, 0, null, null, Arrays.asList(1));
        productSub.of(9L, 1L, 1L, 2L, "메추리알", null, "7알",4, 0, null, null, Arrays.asList(1));
        productSub.of(10L, 1L, 1L, 2L, "오이소박이", null, "90g",5, 0, null, null, Arrays.asList(1));
        productSub.of(11L, 1L, 1L, 2L, "깻잎절임", null, "18g",6, 0, null, null, Arrays.asList(1));
        productSub.of(12L, 1L, 1L, 2L, "무말랭이", null, "60g",7, 0, null, null, Arrays.asList(1));
        productSub.of(13L, 1L, 1L, 2L, "낙지젓갈", null, "50g",8, 0, null, null, Arrays.asList(1));
        productSub.of(14L, 1L, 1L, 2L, "오징어젓갈", null, "50g",9, 0, null, null, Arrays.asList(1));
        productSub.of(15L, 1L, 1L, 2L, "김치", "국내산 김치", "50g",10, 0, null, null, Arrays.asList(1));
        productSub.of(16L, 1L, 1L, 2L, "볶음김치", "국내산 김치", "50g",11, 0, null, null, Arrays.asList(1));
        productSub.of(17L, 1L, 1L, 2L, "제육볶음", null, "85g",1, 0, null, null, Arrays.asList(1));
        productSub.of(18L, 1L, 1L, 2L, "돼지불백", null, "75g",2, 0, null, null, Arrays.asList(1));

        productSub.of(6L, 1L, 1L, 3L, "무생채", null, "60g",1, 0, null, null, Arrays.asList(1));
        productSub.of(7L, 1L, 1L, 3L, "감자볶음", null, "45g",2, 0, null, null, Arrays.asList(1));
        productSub.of(8L, 1L, 1L, 3L, "애호박", null, "60g",3, 0, null, null, Arrays.asList(1));
        productSub.of(9L, 1L, 1L, 3L, "메추리알", null, "7알",4, 0, null, null, Arrays.asList(1));
        productSub.of(10L, 1L, 1L, 3L, "오이소박이", null, "90g",5, 0, null, null, Arrays.asList(1));
        productSub.of(11L, 1L, 1L, 3L, "깻잎절임", null, "18g",6, 0, null, null, Arrays.asList(1));
        productSub.of(12L, 1L, 1L, 3L, "무말랭이", null, "60g",7, 0, null, null, Arrays.asList(1));
        productSub.of(13L, 1L, 1L, 3L, "낙지젓갈", null, "50g",8, 0, null, null, Arrays.asList(1));
        productSub.of(14L, 1L, 1L, 3L, "오징어젓갈", null, "50g",9, 0, null, null, Arrays.asList(1));
        productSub.of(15L, 1L, 1L, 3L, "김치", "국내산 김치", "50g",10, 0, null, null, Arrays.asList(1));
        productSub.of(16L, 1L, 1L, 3L, "볶음김치", "국내산 김치", "50g",11, 0, null, null, Arrays.asList(1));
        productSub.of(17L, 1L, 1L, 3L, "제육볶음", null, "85g",1, 0, null, null, Arrays.asList(1));
        productSub.of(18L, 1L, 1L, 3L, "돼지불백", null, "75g",2, 0, null, null, Arrays.asList(1));

        // 4찬
        productSub.of(4L, 1L, 1L, 1L, "흰쌀밥", null, "185g",1, 0, null, null, Arrays.asList(1));
        productSub.of(5L, 1L, 1L, 1L, "볶음밥", null, "185g",2, 0, null, null, Arrays.asList(1));
        productSub.of(6L, 1L, 1L, 1L, "제육볶음", null, "190g",3, 0, null, null, Arrays.asList(1));
        productSub.of(7L, 1L, 1L, 1L, "돼지불백", null, "180g",4, 0, null, null, Arrays.asList(1));
        productSub.of(6L, 1L, 1L, 2L, "무생채", null, "60g",1, 0, null, null, Arrays.asList(1));
        productSub.of(7L, 1L, 1L, 2L, "감자볶음", null, "45g",2, 0, null, null, Arrays.asList(1));
        productSub.of(8L, 1L, 1L, 2L, "애호박", null, "60g",3, 0, null, null, Arrays.asList(1));
        productSub.of(9L, 1L, 1L, 2L, "메추리알", null, "7알",4, 0, null, null, Arrays.asList(1));
        productSub.of(10L, 1L, 1L, 2L, "오이소박이", null, "90g",5, 0, null, null, Arrays.asList(1));
        productSub.of(11L, 1L, 1L, 2L, "깻잎절임", null, "18g",6, 0, null, null, Arrays.asList(1));
        productSub.of(12L, 1L, 1L, 2L, "무말랭이", null, "60g",7, 0, null, null, Arrays.asList(1));
        productSub.of(13L, 1L, 1L, 2L, "낙지젓갈", null, "50g",8, 0, null, null, Arrays.asList(1));
        productSub.of(14L, 1L, 1L, 2L, "오징어젓갈", null, "50g",9, 0, null, null, Arrays.asList(1));
        productSub.of(15L, 1L, 1L, 2L, "김치", "국내산 김치", "250g",10, 0, null, null, Arrays.asList(1));
        productSub.of(16L, 1L, 1L, 2L, "볶음김치", "국내산 김치", "250g",11, 0, null, null, Arrays.asList(1));

        productSub.of(17L, 1L, 1L, 3L, "제육볶음", null, "85g",1, 0, null, null, Arrays.asList(1));
        productSub.of(18L, 1L, 1L, 3L, "돼지불백", null, "75g",2, 0, null, null, Arrays.asList(1));

        /*
         * 제품-서브 연결
         */
        productSubMapper.of(1L, 4L, 5L, 6L, 7L);
        productSubMapper.of(2L, 4L, 5L, 6L, 7L);
        productSubMapper.of(3L, 4L, 5L, 6L, 7L);


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
        /// 한경
        orderTime.of(13L, LocalTime.parse("11:30:00"), LocalTime.parse("11:45:00"), LocalTime.parse("12:00:00"));
        orderTime.of(14L, LocalTime.parse("17:30:00"), LocalTime.parse("17:45:00"), LocalTime.parse("18:00:00"));
        /// 28청춘
        orderTime.of(15L, LocalTime.parse("11:30:00"), LocalTime.parse("11:45:00"), LocalTime.parse("12:00:00"));
        orderTime.of(16L, LocalTime.parse("17:30:00"), LocalTime.parse("17:45:00"), LocalTime.parse("18:00:00"));


        /*
         * 시간표 연결
         */
        orderTimeMapper.of(1L, 1L, 2L, 3L, 4L);     // 항공대 맘스터치
        orderTimeMapper.of(2L, 1L, 2L, 3L, 4L);     // 항공대 한솥도시락
        orderTimeMapper.of(3L, 9L, 10L, 11L, 12L);  // 연세대 미래캠 맘스터치
        orderTimeMapper.of(4L, 9L, 10L, 11L, 12L);  // 연세대 미래캠 한솥도시락
        orderTimeMapper.of(5L, 5L, 6L, 7L, 8L);     // 항공대 피자매니
        orderTimeMapper.of(6L, 5L, 6L, 7L, 8L);     // 항공대 항공반점
        orderTimeMapper.of(7L, 1L, 2L, 3L, 4L, 13L, 14L, 15L, 16L);     // 포만감 도시락
        orderTimeMapper.of(8L, 13L, 14L, 15L, 16L);     // 반찬탁

        orderTimeDeliverySiteMapper.of(1L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L); // 항공대 시간표
        orderTimeDeliverySiteMapper.of(2L, 9L, 10L, 11L, 12L);              // 연세대 시간표
        orderTimeDeliverySiteMapper.of(3L, 13L, 14L, 15L, 16L);             // 한경 시간표
        orderTimeDeliverySiteMapper.of(4L, 13L, 14L, 15L, 16L);             // 28청춘 시간표


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
        order.of(3L, 3, 1L, LocalDate.now(), 2L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 1);
        order.of(4L, 4, 1L, LocalDate.now(), 1L, PaymentType.COMMON_CREDIT_CARD, OrderType.DELIVERY_READY,
                1L, 1L, 1);
        order.of(5L, 5, 1L, LocalDate.now(), 1L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 2);
        order.of(6L, 6, 1L, LocalDate.now(), 2L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                1L, 1L, 1);
        order.of(7L, 7, 1L, LocalDate.now(), 1L, PaymentType.COMMON_CREDIT_CARD, OrderType.DELIVERY_READY,
                1L, 1L, 1);
        order.of(8L, 8, 1L, LocalDate.now(), 10L, PaymentType.COMMON_CREDIT_CARD, OrderType.ORDER_READY,
                3L, 1L, 2);
        order.of(9L, 9, 1L, LocalDate.now(), 10L, PaymentType.COMMON_CREDIT_CARD, OrderType.DELIVERY_READY,
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
