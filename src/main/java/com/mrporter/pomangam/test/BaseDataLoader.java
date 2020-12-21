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
            run();
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
                Arrays.asList(1,2,3,4,5,6,7),
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
                1L, 1L, 1, 0, 0, 0,
                1,2);
        product.of(2L,"도시락(M)", ProductType.CUSTOMIZING_4,
                1L, 1L, 2, 0, 0, 0,
                1,2);
        product.of(3L,"도시락(L)", ProductType.CUSTOMIZING_5,
                1L, 1L, 3, 0, 0, 0,
                1,2);


        /*
         * 서브 카테고리
         */
        // 3찬
        productSubCategory.of(1L, "필수선택1", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(2L, "필수선택2", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(3L, "필수선택3", ProductSubType.CUSTOMIZING_SUB, true);

        // 4찬
        productSubCategory.of(4L, "필수선택1", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(5L, "필수선택2", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(6L, "필수선택3", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(7L, "필수선택4", ProductSubType.CUSTOMIZING_SUB, true);

        // 5찬
        productSubCategory.of(8L, "필수선택1", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(9L, "필수선택2", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(10L, "필수선택3", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(11L, "필수선택4", ProductSubType.CUSTOMIZING_SUB, true);
        productSubCategory.of(12L, "필수선택5", ProductSubType.CUSTOMIZING_SUB, true);

        productSubCategory.of(13L, "국", ProductSubType.CUSTOMIZING_SUB, false);
        productSubCategory.of(14L, "음료", ProductSubType.CUSTOMIZING_SUB, false);


        /*
         * 서브 제품
         */

        // 3찬
//        productSub.of(1L, 1L, 1L, 7L, "콜라", "코카콜라", "500ml",1, 1000, null, null, Arrays.asList(1), 1L);
//        productSub.of(2L, 1L, 1L, 7L, "사이다", "칠성사이다", "500ml",2, 1000, null, null, Arrays.asList(1), 1L);
//        productSub.of(3L, 1L, 1L, 6L, "김치찌개", "", "",1, 3000, null, null, Arrays.asList(1), 1L);
        // 3찬 하단 (1)
        productSub.of(1L, 1L, 1L, 1L, "흰쌀밥", null, "250g",1, 2000, null, null, 1, 1L);
        productSub.of(2L, 1L, 1L, 1L, "흰쌀밥+계란후라이", null, "250g",2, 2500, null, null, 2, 1L);
        productSub.of(3L, 1L, 1L, 1L, "제육볶음", null, "200g",3, 3000, null, null, 3, 1L);
        productSub.of(4L, 1L, 1L, 1L, "돼지불백", null, "190g",4, 3000, null, null, 4, 1L);
        // 3찬 상단 왼쪽 (2)
        productSub.of(5L, 1L, 1L, 2L, "흰쌀밥", null, "85g",1, 1000, null, null, 1, 1L);
        productSub.of(6L, 1L, 1L, 2L, "흰쌀밥+계란후라이", null, "85g",2, 1500, null, null, 2, 1L);
        productSub.of(18L, 1L, 1L, 2L, "제육볶음", null, "85g",3, 1500, null, null, 3, 1L);
        productSub.of(19L, 1L, 1L, 2L, "돼지불백", null, "75g",4, 1500, null, null, 4, 1L);
        productSub.of(7L, 1L, 1L, 2L, "무생채", null, "60g",5, 900, null, null, 7, 1L);
        productSub.of(8L, 1L, 1L, 2L, "감자볶음", null, "45g",6, 900, null, null, 8, 1L);
        productSub.of(9L, 1L, 1L, 2L, "애호박볶음", null, "60g",7, 900, null, null, 9, 1L);
        productSub.of(10L, 1L, 1L, 2L, "메추리알", null, "7알",8, 1300, null, null, 10, 1L);
        productSub.of(11L, 1L, 1L, 2L, "오이소박이", null, "90g",9, 1300, null, null, 11, 1L);
        productSub.of(12L, 1L, 1L, 2L, "깻잎절임", null, "18g",10, 900, null, null, 12, 1L);
        productSub.of(13L, 1L, 1L, 2L, "무말랭이", null, "60g",11, 900, null, null, 13, 1L);
        productSub.of(14L, 1L, 1L, 2L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 1L);
        productSub.of(15L, 1L, 1L, 2L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 1L);
        productSub.of(16L, 1L, 1L, 2L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 1L);
        productSub.of(17L, 1L, 1L, 2L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 1L);
        productSub.of(18L, 1L, 1L, 2L, "어묵볶음", null, "50g",16, 900, null, null, 18, 1L);
        productSub.of(19L, 1L, 1L, 2L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 1L);
        productSub.of(20L, 1L, 1L, 2L, "멸치볶음", null, "30g",18, 900, null, null, 20, 1L);
        productSub.of(21L, 1L, 1L, 2L, "미트볼", null, "50g",19, 900, null, null, 21, 1L);
        productSub.of(22L, 1L, 1L, 2L, "진미채", null, "50g",29, 900, null, null, 22, 1L);
        productSub.of(23L, 1L, 1L, 2L, "콩나물무침", null, "50g",21, 900, null, null, 23, 1L);

        // 3찬 상단 오른쪽 (3)
        productSub.of(4L, 1L, 1L, 3L, "흰쌀밥", null, "85g",1, 1000, null, null, 1, 1L);
        productSub.of(5L, 1L, 1L, 3L, "흰쌀밥+계란후라이", null, "85g",2, 1500, null, null, 2, 1L);
        productSub.of(17L, 1L, 1L, 3L, "제육볶음", null, "85g",3, 1500, null, null, 3, 1L);
        productSub.of(18L, 1L, 1L, 3L, "돼지불백", null, "75g",4, 1500, null, null, 4, 1L);
        productSub.of(6L, 1L, 1L, 3L, "무생채", null, "60g",5, 900, null, null, 7, 1L);
        productSub.of(7L, 1L, 1L, 3L, "감자볶음", null, "45g",6, 900, null, null, 8, 1L);
        productSub.of(8L, 1L, 1L, 3L, "애호박볶음", null, "60g",7, 900, null, null, 9, 1L);
        productSub.of(9L, 1L, 1L, 3L, "메추리알", null, "7알",8, 1300, null, null, 10, 1L);
        productSub.of(10L, 1L, 1L, 3L, "오이소박이", null, "90g",9, 1300, null, null, 11, 1L);
        productSub.of(11L, 1L, 1L, 3L, "깻잎절임", null, "18g",10, 900, null, null, 12, 1L);
        productSub.of(12L, 1L, 1L, 3L, "무말랭이", null, "60g",11, 900, null, null, 13, 1L);
        productSub.of(13L, 1L, 1L, 3L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 1L);
        productSub.of(14L, 1L, 1L, 3L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 1L);
        productSub.of(15L, 1L, 1L, 3L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 1L);
        productSub.of(16L, 1L, 1L, 3L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 1L);
        productSub.of(18L, 1L, 1L, 3L, "어묵볶음", null, "50g",16, 900, null, null, 18, 1L);
        productSub.of(19L, 1L, 1L, 3L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 1L);
        productSub.of(20L, 1L, 1L, 3L, "멸치볶음", null, "30g",18, 900, null, null, 20, 1L);
        productSub.of(21L, 1L, 1L, 3L, "미트볼", null, "50g",19, 900, null, null, 21, 1L);
        productSub.of(22L, 1L, 1L, 3L, "진미채", null, "50g",20, 900, null, null, 22, 1L);
        productSub.of(23L, 1L, 1L, 3L, "콩나물무침", null, "50g",21, 900, null, null, 23, 1L);


        // 4찬
//        productSub.of(1L, 1L, 1L, 7L, "콜라", "코카콜라", "500ml",1, 1000, null, null, Arrays.asList(1), 2L);
//        productSub.of(2L, 1L, 1L, 7L, "사이다", "칠성사이다", "500ml",2, 1000, null, null, Arrays.asList(1), 2L);
//        productSub.of(3L, 1L, 1L, 6L, "김치찌개", "", "",1, 3000, null, null, Arrays.asList(1), 2L);
        // 4찬 하단 왼쪽 (1)
        productSub.of(4L, 1L, 1L, 4L, "흰쌀밥", null, "85g",1, 1000, null, null, 1, 2L);
        productSub.of(5L, 1L, 1L, 4L, "흰쌀밥+계란후라이", null, "85g",2, 1500, null, null, 2, 2L);
        productSub.of(17L, 1L, 1L, 4L, "제육볶음", null, "130g",3, 2100, null, null, 3, 2L);
        productSub.of(18L, 1L, 1L, 4L, "돼지불백", null, "120g",4, 2100, null, null, 4, 2L);
        productSub.of(6L, 1L, 1L, 4L, "무생채", null, "60g",5, 900, null, null, 7, 2L);
        productSub.of(7L, 1L, 1L, 4L, "감자볶음", null, "45g",6, 900, null, null, 8, 2L);
        productSub.of(8L, 1L, 1L, 4L, "애호박볶음", null, "60g",7, 900, null, null, 9, 2L);
        productSub.of(9L, 1L, 1L, 4L, "메추리알", null, "7알",8, 1300, null, null, 10, 2L);
        productSub.of(10L, 1L, 1L, 4L, "오이소박이", null, "90g",9, 1300, null, null, 11, 2L);
        productSub.of(11L, 1L, 1L, 4L, "깻잎절임", null, "18g",10, 900, null, null, 12, 2L);
        productSub.of(12L, 1L, 1L, 4L, "무말랭이", null, "60g",11, 900, null, null, 13, 2L);
        productSub.of(13L, 1L, 1L, 4L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 2L);
        productSub.of(14L, 1L, 1L, 4L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 2L);
        productSub.of(15L, 1L, 1L, 4L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 2L);
        productSub.of(16L, 1L, 1L, 4L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 2L);
        productSub.of(18L, 1L, 1L, 4L, "어묵볶음", null, "50g",16, 900, null, null, 18, 2L);
        productSub.of(19L, 1L, 1L, 4L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 2L);
        productSub.of(20L, 1L, 1L, 4L, "멸치볶음", null, "30g",18, 900, null, null, 20, 2L);
        productSub.of(21L, 1L, 1L, 4L, "미트볼", null, "50g",19, 900, null, null, 21, 2L);
        productSub.of(22L, 1L, 1L, 4L, "진미채", null, "50g",20, 900, null, null, 22, 2L);
        productSub.of(23L, 1L, 1L, 4L, "콩나물무침", null, "50g",21, 900, null, null, 23, 2L);

        // 4찬 하단 오른쪽 (2)
        productSub.of(4L, 1L, 1L, 5L, "흰쌀밥", null, "85g",1, 1000, null, null, 1, 2L);
        productSub.of(5L, 1L, 1L, 5L, "흰쌀밥+계란후라이", null, "85g",2, 1500, null, null, 2, 2L);
        productSub.of(17L, 1L, 1L, 5L, "제육볶음", null, "190g",3, 3000, null, null, 3, 2L);
        productSub.of(18L, 1L, 1L, 5L, "돼지불백", null, "180g",4, 3000, null, null, 4, 2L);
        productSub.of(6L, 1L, 1L, 5L, "무생채", null, "60g",5, 900, null, null, 7, 2L);
        productSub.of(7L, 1L, 1L, 5L, "감자볶음", null, "45g",6, 900, null, null, 8, 2L);
        productSub.of(8L, 1L, 1L, 5L, "애호박볶음", null, "60g",7, 900, null, null, 9, 2L);
        productSub.of(9L, 1L, 1L, 5L, "메추리알", null, "7알",8, 1300, null, null, 10, 2L);
        productSub.of(10L, 1L, 1L, 5L, "오이소박이", null, "90g",9, 1300, null, null, 11, 2L);
        productSub.of(11L, 1L, 1L, 5L, "깻잎절임", null, "18g",10, 900, null, null, 12, 2L);
        productSub.of(12L, 1L, 1L, 5L, "무말랭이", null, "60g",11, 900, null, null, 13, 2L);
        productSub.of(13L, 1L, 1L, 5L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 2L);
        productSub.of(14L, 1L, 1L, 5L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 2L);
        productSub.of(15L, 1L, 1L, 5L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 2L);
        productSub.of(16L, 1L, 1L, 5L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 2L);
        productSub.of(18L, 1L, 1L, 5L, "어묵볶음", null, "50g",16, 900, null, null, 18, 2L);
        productSub.of(19L, 1L, 1L, 5L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 2L);
        productSub.of(20L, 1L, 1L, 5L, "멸치볶음", null, "30g",18, 900, null, null, 20, 2L);
        productSub.of(21L, 1L, 1L, 5L, "미트볼", null, "50g",19, 900, null, null, 21, 2L);
        productSub.of(22L, 1L, 1L, 5L, "진미채", null, "50g",20, 900, null, null, 22, 2L);
        productSub.of(23L, 1L, 1L, 5L, "콩나물무침", null, "50g",21, 900, null, null, 23, 2L);

        // 4찬 상단 왼쪽 (3)
        productSub.of(4L, 1L, 1L, 6L, "흰쌀밥", null, "85g",1, 1000, null, null, 1, 2L);
        productSub.of(17L, 1L, 1L, 6L, "제육볶음", null, "85g",3, 1500, null, null, 3, 2L);
        productSub.of(18L, 1L, 1L, 6L, "돼지불백", null, "75g",4, 1500, null, null, 4, 2L);
        productSub.of(6L, 1L, 1L, 6L, "무생채", null, "60g",5, 900, null, null, 7, 2L);
        productSub.of(7L, 1L, 1L, 6L, "감자볶음", null, "45g",6, 900, null, null, 8, 2L);
        productSub.of(8L, 1L, 1L, 6L, "애호박볶음", null, "60g",7, 900, null, null, 9, 2L);
        productSub.of(9L, 1L, 1L, 6L, "메추리알", null, "7알",8, 1300, null, null, 10, 2L);
        productSub.of(10L, 1L, 1L, 6L, "오이소박이", null, "90g",9, 1300, null, null, 11, 2L);
        productSub.of(11L, 1L, 1L, 6L, "깻잎절임", null, "18g",10, 900, null, null, 12, 2L);
        productSub.of(12L, 1L, 1L, 6L, "무말랭이", null, "60g",11, 900, null, null, 13, 2L);
        productSub.of(13L, 1L, 1L, 6L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 2L);
        productSub.of(14L, 1L, 1L, 6L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 2L);
        productSub.of(15L, 1L, 1L, 6L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 2L);
        productSub.of(16L, 1L, 1L, 6L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 2L);
        productSub.of(18L, 1L, 1L, 6L, "어묵볶음", null, "50g",16, 900, null, null, 18, 2L);
        productSub.of(19L, 1L, 1L, 6L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 2L);
        productSub.of(20L, 1L, 1L, 6L, "멸치볶음", null, "30g",18, 900, null, null, 20, 2L);
        productSub.of(21L, 1L, 1L, 6L, "미트볼", null, "50g",19, 900, null, null, 21, 2L);
        productSub.of(22L, 1L, 1L, 6L, "진미채", null, "50g",20, 900, null, null, 22, 2L);
        productSub.of(23L, 1L, 1L, 6L, "콩나물무침", null, "50g",21, 900, null, null, 23, 2L);

        // 4찬 상단 오른쪽 (4)
        productSub.of(6L, 1L, 1L, 7L, "무생채", null, "60g",5, 900, null, null, 7, 2L);
        productSub.of(7L, 1L, 1L, 7L, "감자볶음", null, "45g",6, 900, null, null, 8, 2L);
        productSub.of(8L, 1L, 1L, 7L, "애호박볶음", null, "60g",7, 900, null, null, 9, 2L);
        productSub.of(12L, 1L, 1L, 7L, "무말랭이", null, "60g",11, 900, null, null, 13, 2L);
        productSub.of(13L, 1L, 1L, 7L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 2L);
        productSub.of(14L, 1L, 1L, 7L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 2L);
        productSub.of(15L, 1L, 1L, 7L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 2L);
        productSub.of(16L, 1L, 1L, 7L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 2L);
        productSub.of(18L, 1L, 1L, 7L, "어묵볶음", null, "50g",16, 900, null, null, 18, 2L);
        productSub.of(20L, 1L, 1L, 7L, "멸치볶음", null, "30g",18, 900, null, null, 20, 2L);
        productSub.of(22L, 1L, 1L, 7L, "진미채", null, "50g",20, 900, null, null, 22, 2L);
        productSub.of(23L, 1L, 1L, 7L, "콩나물무침", null, "50g",21, 900, null, null, 23, 2L);


        // 5찬
//        productSub.of(1L, 1L, 1L, 7L, "콜라", "코카콜라", "500ml",1, 1000, null, null, Arrays.asList(1), 3L);
//        productSub.of(2L, 1L, 1L, 7L, "사이다", "칠성사이다", "500ml",2, 1000, null, null, Arrays.asList(1), 3L);
//        productSub.of(3L, 1L, 1L, 6L, "김치찌개", "", "",1, 3000, null, null, Arrays.asList(1), 3L);
        // 5찬 하단 왼쪽 (1)
        productSub.of(4L, 1L, 1L, 8L, "흰쌀밥", null, "185g",1, 1000, null, null, 1, 3L);
        productSub.of(5L, 1L, 1L, 8L, "흰쌀밥+계란후라이", null, "185g",2, 1500, null, null, 2, 3L);
        productSub.of(17L, 1L, 1L, 8L, "제육볶음", null, "130g",3, 2100, null, null, 3, 3L);
        productSub.of(18L, 1L, 1L, 8L, "돼지불백", null, "120g",4, 2100, null, null, 4, 3L);
        productSub.of(6L, 1L, 1L, 8L, "무생채", null, "60g",5, 900, null, null, 7, 3L);
        productSub.of(7L, 1L, 1L, 8L, "감자볶음", null, "45g",6, 900, null, null, 8, 3L);
        productSub.of(8L, 1L, 1L, 8L, "애호박볶음", null, "60g",7, 900, null, null, 9, 3L);
        productSub.of(9L, 1L, 1L, 8L, "메추리알", null, "7알",8, 1300, null, null, 10, 3L);
        productSub.of(10L, 1L, 1L, 8L, "오이소박이", null, "90g",9, 1300, null, null, 11, 3L);
        productSub.of(11L, 1L, 1L, 8L, "깻잎절임", null, "18g",10, 900, null, null, 12, 3L);
        productSub.of(12L, 1L, 1L, 8L, "무말랭이", null, "60g",11, 900, null, null, 13, 3L);
        productSub.of(13L, 1L, 1L, 8L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 3L);
        productSub.of(14L, 1L, 1L, 8L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 3L);
        productSub.of(15L, 1L, 1L, 8L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 3L);
        productSub.of(16L, 1L, 1L, 8L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 3L);
        productSub.of(18L, 1L, 1L, 8L, "어묵볶음", null, "50g",16, 900, null, null, 18, 3L);
        productSub.of(19L, 1L, 1L, 8L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 3L);
        productSub.of(20L, 1L, 1L, 8L, "멸치볶음", null, "30g",18, 900, null, null, 20, 3L);
        productSub.of(21L, 1L, 1L, 8L, "미트볼", null, "50g",19, 900, null, null, 21, 3L);
        productSub.of(22L, 1L, 1L, 8L, "진미채", null, "50g",20, 900, null, null, 22, 3L);
        productSub.of(23L, 1L, 1L, 8L, "콩나물무침", null, "50g",21, 900, null, null, 23, 3L);

        // 5찬 하단 오른쪽 (2)
        productSub.of(4L, 1L, 1L, 9L, "흰쌀밥", null, "185g",1, 1000, null, null, 1, 3L);
        productSub.of(5L, 1L, 1L, 9L, "흰쌀밥+계란후라이", null, "185g",2, 1500, null, null, 2, 3L);
        productSub.of(17L, 1L, 1L, 9L, "제육볶음", null, "85g",3, 1500, null, null, 3, 3L);
        productSub.of(18L, 1L, 1L, 9L, "돼지불백", null, "75g",4, 1500, null, null, 4, 3L);
        productSub.of(6L, 1L, 1L, 9L, "무생채", null, "60g",5, 900, null, null, 7, 3L);
        productSub.of(7L, 1L, 1L, 9L, "감자볶음", null, "45g",6, 900, null, null, 8, 3L);
        productSub.of(8L, 1L, 1L, 9L, "애호박볶음", null, "60g",7, 900, null, null, 9, 3L);
        productSub.of(9L, 1L, 1L, 9L, "메추리알", null, "7알",8, 1300, null, null, 10, 3L);
        productSub.of(10L, 1L, 1L, 9L, "오이소박이", null, "90g",9, 1300, null, null, 11, 3L);
        productSub.of(11L, 1L, 1L, 9L, "깻잎절임", null, "18g",10, 900, null, null, 12, 3L);
        productSub.of(12L, 1L, 1L, 9L, "무말랭이", null, "60g",11, 900, null, null, 13, 3L);
        productSub.of(13L, 1L, 1L, 9L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 3L);
        productSub.of(14L, 1L, 1L, 9L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 3L);
        productSub.of(15L, 1L, 1L, 9L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 3L);
        productSub.of(16L, 1L, 1L, 9L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 3L);
        productSub.of(18L, 1L, 1L, 9L, "어묵볶음", null, "50g",16, 900, null, null, 18, 3L);
        productSub.of(19L, 1L, 1L, 9L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 3L);
        productSub.of(20L, 1L, 1L, 9L, "멸치볶음", null, "30g",18, 900, null, null, 20, 3L);
        productSub.of(21L, 1L, 1L, 9L, "미트볼", null, "50g",19, 900, null, null, 21, 3L);
        productSub.of(22L, 1L, 1L, 9L, "진미채", null, "50g",20, 900, null, null, 22, 3L);
        productSub.of(23L, 1L, 1L, 9L, "콩나물무침", null, "50g",21, 900, null, null, 23, 3L);

        // 5찬 상단 왼쪽 (3)
        productSub.of(4L, 1L, 1L, 10L, "흰쌀밥", null, "185g",1, 1000, null, null, 1, 3L);
        productSub.of(5L, 1L, 1L, 10L, "흰쌀밥+계란후라이", null, "185g",2, 1500, null, null, 2, 3L);
        productSub.of(17L, 1L, 1L, 10L, "제육볶음", null, "85g",3, 1500, null, null, 3, 3L);
        productSub.of(18L, 1L, 1L, 10L, "돼지불백", null, "75g",4, 1500, null, null, 4, 3L);
        productSub.of(6L, 1L, 1L, 10L, "무생채", null, "60g",5, 900, null, null, 7, 3L);
        productSub.of(7L, 1L, 1L, 10L, "감자볶음", null, "45g",6, 900, null, null, 8, 3L);
        productSub.of(8L, 1L, 1L, 10L, "애호박볶음", null, "60g",7, 900, null, null, 9, 3L);
        productSub.of(9L, 1L, 1L, 10L, "메추리알", null, "7알",8, 1300, null, null, 10, 3L);
        productSub.of(11L, 1L, 1L, 10L, "깻잎절임", null, "18g",10, 900, null, null, 12, 3L);
        productSub.of(12L, 1L, 1L, 10L, "무말랭이", null, "60g",11, 900, null, null, 13, 3L);
        productSub.of(13L, 1L, 1L, 10L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 3L);
        productSub.of(14L, 1L, 1L, 10L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 3L);
        productSub.of(15L, 1L, 1L, 10L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 3L);
        productSub.of(16L, 1L, 1L, 10L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 3L);
        productSub.of(18L, 1L, 1L, 10L, "어묵볶음", null, "50g",16, 900, null, null, 18, 3L);
        productSub.of(19L, 1L, 1L, 10L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 3L);
        productSub.of(20L, 1L, 1L, 10L, "멸치볶음", null, "30g",18, 900, null, null, 20, 3L);
        productSub.of(21L, 1L, 1L, 10L, "미트볼", null, "50g",19, 900, null, null, 21, 3L);
        productSub.of(22L, 1L, 1L, 10L, "진미채", null, "50g",20, 900, null, null, 22, 3L);
        productSub.of(23L, 1L, 1L, 10L, "콩나물무침", null, "50g",21, 900, null, null, 23, 3L);

        // 5찬 상단 중앙 (4)
        productSub.of(4L, 1L, 1L, 11L, "흰쌀밥", null, "185g",1, 1000, null, null, 1, 3L);
        productSub.of(5L, 1L, 1L, 11L, "흰쌀밥+계란후라이", null, "185g",2, 1500, null, null, 2, 3L);
        productSub.of(17L, 1L, 1L, 11L, "제육볶음", null, "85g",3, 1500, null, null, 3, 3L);
        productSub.of(18L, 1L, 1L, 11L, "돼지불백", null, "75g",4, 1500, null, null, 4, 3L);
        productSub.of(6L, 1L, 1L, 11L, "무생채", null, "60g",5, 900, null, null, 7, 3L);
        productSub.of(7L, 1L, 1L, 11L, "감자볶음", null, "45g",6, 900, null, null, 8, 3L);
        productSub.of(8L, 1L, 1L, 11L, "애호박볶음", null, "60g",7, 900, null, null, 9, 3L);
        productSub.of(9L, 1L, 1L, 11L, "메추리알", null, "7알",8, 1300, null, null, 10, 3L);
        productSub.of(11L, 1L, 1L, 11L, "깻잎절임", null, "18g",10, 900, null, null, 12, 3L);
        productSub.of(12L, 1L, 1L, 11L, "무말랭이", null, "60g",11, 900, null, null, 13, 3L);
        productSub.of(13L, 1L, 1L, 11L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 3L);
        productSub.of(14L, 1L, 1L, 11L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 3L);
        productSub.of(15L, 1L, 1L, 11L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 3L);
        productSub.of(16L, 1L, 1L, 11L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 3L);
        productSub.of(18L, 1L, 1L, 11L, "어묵볶음", null, "50g",16, 900, null, null, 18, 3L);
        productSub.of(19L, 1L, 1L, 11L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 3L);
        productSub.of(20L, 1L, 1L, 11L, "멸치볶음", null, "30g",18, 900, null, null, 20, 3L);
        productSub.of(21L, 1L, 1L, 11L, "미트볼", null, "50g",19, 900, null, null, 21, 3L);
        productSub.of(22L, 1L, 1L, 11L, "진미채", null, "50g",20, 900, null, null, 22, 3L);
        productSub.of(23L, 1L, 1L, 11L, "콩나물무침", null, "50g",21, 900, null, null, 23, 3L);

        // 5찬 상단 오른쪽 (5)
        productSub.of(4L, 1L, 1L, 12L, "흰쌀밥", null, "185g",1, 1000, null, null, 1, 3L);
        productSub.of(5L, 1L, 1L, 12L, "흰쌀밥+계란후라이", null, "185g",2, 1500, null, null, 2, 3L);
        productSub.of(17L, 1L, 1L, 12L, "제육볶음", null, "85g",3, 1500, null, null, 3, 3L);
        productSub.of(18L, 1L, 1L, 12L, "돼지불백", null, "75g",4, 1500, null, null, 4, 3L);
        productSub.of(6L, 1L, 1L, 12L, "무생채", null, "60g",5, 900, null, null, 7, 3L);
        productSub.of(7L, 1L, 1L, 12L, "감자볶음", null, "45g",6, 900, null, null, 8, 3L);
        productSub.of(8L, 1L, 1L, 12L, "애호박볶음", null, "60g",7, 900, null, null, 9, 3L);
        productSub.of(9L, 1L, 1L, 12L, "메추리알", null, "7알",8, 1300, null, null, 10, 3L);
        productSub.of(11L, 1L, 1L, 12L, "깻잎절임", null, "18g",10, 900, null, null, 12, 3L);
        productSub.of(12L, 1L, 1L, 12L, "무말랭이", null, "60g",11, 900, null, null, 13, 3L);
        productSub.of(13L, 1L, 1L, 12L, "낙지젓갈", null, "50g",12, 900, null, null, 14, 3L);
        productSub.of(14L, 1L, 1L, 12L, "오징어젓갈", null, "50g",13, 900, null, null, 15, 3L);
        productSub.of(15L, 1L, 1L, 12L, "김치", "국내산 김치", "50g",14, 900, null, null, 16, 3L);
        productSub.of(16L, 1L, 1L, 12L, "볶음김치", "국내산 김치", "50g",15, 900, null, null, 17, 3L);
        productSub.of(18L, 1L, 1L, 12L, "어묵볶음", null, "50g",16, 900, null, null, 18, 3L);
        productSub.of(19L, 1L, 1L, 12L, "소세지 야채볶음", null, "50g",17, 900, null, null, 19, 3L);
        productSub.of(20L, 1L, 1L, 12L, "멸치볶음", null, "30g",18, 900, null, null, 20, 3L);
        productSub.of(21L, 1L, 1L, 12L, "미트볼", null, "50g",19, 900, null, null, 21, 3L);
        productSub.of(22L, 1L, 1L, 12L, "진미채", null, "50g",20, 900, null, null, 22, 3L);
        productSub.of(23L, 1L, 1L, 12L, "콩나물무침", null, "50g",21, 900, null, null, 23, 3L);


        /*
         * 시간표
         */
        orderTime.of(1L, LocalTime.parse("11:30:00"), LocalTime.parse("11:45:00"), LocalTime.parse("12:00:00"));
        orderTime.of(2L, LocalTime.parse("12:30:00"), LocalTime.parse("12:45:00"), LocalTime.parse("13:00:00"));
        orderTime.of(3L, LocalTime.parse("17:30:00"), LocalTime.parse("17:45:00"), LocalTime.parse("18:00:00"));
        orderTime.of(4L, LocalTime.parse("18:30:00"), LocalTime.parse("18:45:00"), LocalTime.parse("19:00:00"));


        /*
         * 시간표 연결
         */
        orderTimeMapper.of(1L, 1L, 2L, 3L, 4L);     // 항공대 맘스터치
        orderTimeDeliverySiteMapper.of(1L, 1L, 2L, 3L, 4L); // 항공대 시간표

        /*
         * 업주
         */
        fcmToken.of(1L, "__FCM_TOKEN_1__");
        storeOwner.of(1L, 1L, 1L, "eunstar_v", "1234!", "최은성", "010-5843-0627", Sex.MALE, LocalDate.now(), null);
        storeOwner.of(2L, 1L, 1L, "nteve", "skrwl486", "최낙형", "010-6478-4899", Sex.MALE, LocalDate.now(), null);
        storeOwner.of(3L, 1L, 1L, "pmg", "qlqj1!", "김기훈", "010-6209-4446", Sex.MALE, LocalDate.now(), null);

        /*
         * 관리자
         */
        staffData.of(1L, 1L, "admin", "eun*zzi92!", "관리자", "010-6478-4899", Sex.MALE, LocalDate.now(), "ROLE_ADMIN");
    }
}
