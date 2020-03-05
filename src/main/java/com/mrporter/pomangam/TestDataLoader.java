package com.mrporter.pomangam;

import com.mrporter.pomangam.client.domains.advertisement.Advertisement;
import com.mrporter.pomangam.client.domains.advertisement.AdvertisementMapper;
import com.mrporter.pomangam.client.domains.advertisement.AdvertisementType;
import com.mrporter.pomangam.client.domains.coupon.Coupon;
import com.mrporter.pomangam.client.domains.coupon.CouponMapper;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSiteDto;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.event.Event;
import com.mrporter.pomangam.client.domains.event.EventMapper;
import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.item.OrderItem;
import com.mrporter.pomangam.client.domains.order.orderer.Orderer;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.order.payment_info.PaymentInfo;
import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.ordertime.OrderTimeMapper;
import com.mrporter.pomangam.client.domains.payment.Payment;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import com.mrporter.pomangam.client.domains.product.cost.Cost;
import com.mrporter.pomangam.client.domains.product.image.ProductImage;
import com.mrporter.pomangam.client.domains.product.image.ProductImageType;
import com.mrporter.pomangam.client.domains.product.info.ProductInfo;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubMapper;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubType;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.domains.product.sub.image.ProductSubImage;
import com.mrporter.pomangam.client.domains.product.sub.image.ProductSubImageType;
import com.mrporter.pomangam.client.domains.product.sub.info.ProductSubInfo;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import com.mrporter.pomangam.client.domains.store.image.StoreImageType;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.domains.store.info.StoreInfo;
import com.mrporter.pomangam.client.domains.store.schedule.StoreSchedule;
import com.mrporter.pomangam.client.domains.store.story.StoreStory;
import com.mrporter.pomangam.client.domains.store.story.image.StoreStoryImage;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.domains.user.password.Password;
import com.mrporter.pomangam.client.domains.user.point.rank.PointRank;
import com.mrporter.pomangam.client.repositories.advertisement.AdvertisementJpaRepository;
import com.mrporter.pomangam.client.repositories.advertisement.AdvertisementMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.coupon.CouponJpaRepository;
import com.mrporter.pomangam.client.repositories.coupon.CouponMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.DeliverySiteJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.detail.DeliveryDetailSiteJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.region.RegionJpaRepository;
import com.mrporter.pomangam.client.repositories.event.EventJpaRepository;
import com.mrporter.pomangam.client.repositories.event.EventMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.FcmTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeJpaRepository;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.payment.PaymentJpaRepository;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import com.mrporter.pomangam.client.repositories.product.category.ProductCategoryJpaRepository;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubJpaRepository;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.product.sub.category.ProductSubCategoryJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import com.mrporter.pomangam.client.repositories.user.point.rank.PointRankJpaRepository;
import com.mrporter.pomangam.client.repositories.user.random_nickname.RandomNicknameJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class TestDataLoader implements ApplicationRunner {

    @Autowired StoreCategoryJpaRepository storeCategoryJpaRepository;
    @Autowired StoreJpaRepository storeJpaRepository;
    @Autowired DeliverySiteJpaRepository deliverySiteJpaRepository;
    @Autowired
    DeliveryDetailSiteJpaRepository detailJpaRepository;
    @Autowired RegionJpaRepository regionJpaRepository;
    @Autowired RandomNicknameJpaRepository randomNicknameJpaRepository;
    @Autowired ProductJpaRepository productJpaRepository;
    @Autowired UserServiceImpl userService;
    @Autowired ProductCategoryJpaRepository productCategoryJpaRepository;
    @Autowired ProductSubCategoryJpaRepository productSubCategoryJpaRepository;
    @Autowired ProductSubJpaRepository productSubJpaRepository;
    @Autowired ProductSubMapperJpaRepository productSubMapperJpaRepository;
    @Autowired FcmTokenJpaRepository fcmTokenJpaRepository;
    @Autowired OrderTimeJpaRepository orderTimeJpaRepository;
    @Autowired OrderTimeMapperJpaRepository orderTimeMapperJpaRepository;
    @Autowired OrderJpaRepository orderJpaRepository;
    @Autowired PaymentJpaRepository paymentJpaRepository;
    @Autowired CouponJpaRepository couponJpaRepository;
    @Autowired CouponMapperJpaRepository couponMapperJpaRepository;
    @Autowired PointRankJpaRepository pointRankJpaRepository;
    @Autowired OrderServiceImpl orderService;
    @Autowired AdvertisementJpaRepository advertisementJpaRepository;
    @Autowired AdvertisementMapperJpaRepository advertisementMapperJpaRepository;
    @Autowired EventJpaRepository eventJpaRepository;
    @Autowired EventMapperJpaRepository eventMapperJpaRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(ApplicationArguments args) {
        if( !ddl.equals("create") ) return;

        run();
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

    @Transactional
    void run() {
        Region region = Region.builder()
                .name("경기")
                .build();
        regionJpaRepository.save(region);

        DeliverySite deliverySite = DeliverySite.builder()
                .name("한국항공대")
                .region(region)
                .campus("본캠")
                .location("경기도 고양시 한국항공대학로 123")
                .build();
        DeliverySite deliverySite2 = DeliverySite.builder()
                .name("성균관대")
                .region(region)
                .campus("본캠")
                .location("경기도 화성시 성균관로 123")
                .build();
        deliverySiteJpaRepository.save(deliverySite);
        deliverySiteJpaRepository.save(deliverySite2);


        Advertisement advertisement1 = Advertisement.builder()
                .advertisementType(AdvertisementType.MAIN)
                .imagePath(ImagePath.advertisements(1L, 1L)+"1.jpg")
                .build();
        Advertisement advertisement2 = Advertisement.builder()
                .advertisementType(AdvertisementType.MAIN)
                .imagePath(ImagePath.advertisements(1L, 2L)+"1.jpg")
                .build();
        advertisementJpaRepository.save(advertisement1);
        advertisementJpaRepository.save(advertisement2);

        AdvertisementMapper advertisementMapper1 = AdvertisementMapper.builder()
                .advertisement(advertisement1)
                .deliverySite(deliverySite)
                .build();
        AdvertisementMapper advertisementMapper2 = AdvertisementMapper.builder()
                .advertisement(advertisement2)
                .deliverySite(deliverySite)
                .build();
        advertisementMapperJpaRepository.save(advertisementMapper1);
        advertisementMapperJpaRepository.save(advertisementMapper2);


        Event event1 = Event.builder()
                .beginDate(LocalDateTime.parse("2020-02-15T00:00:00"))
                .endDate(LocalDateTime.parse("2020-09-01T11:59:59"))
                .title("신학기 쿠폰 증정 이벤트")
                .contents("쿠폰을 받기 위해서는 블라블라 빠큐먹엉")
                .build();
        Event event2 = Event.builder()
                .beginDate(LocalDateTime.parse("2020-02-15T00:00:00"))
                .title("1일 1닭 이벤트")
                .contents("1닭 을 받기 위해서는 블라블라 빠큐먹엉")
                .build();
        eventJpaRepository.save(event1);
        eventJpaRepository.save(event2);

        EventMapper eventMapper1 = EventMapper.builder()
                .deliverySite(deliverySite)
                .event(event1)
                .build();
        EventMapper eventMapper2 = EventMapper.builder()
                .deliverySite(deliverySite)
                .event(event2)
                .build();
        eventMapperJpaRepository.save(eventMapper1);
        eventMapperJpaRepository.save(eventMapper2);



        DeliveryDetailSite detail1 = DeliveryDetailSite.builder()
                .name("학생회관 뒤")
                .location("학생회관 뒤 족구장 있는 곳")
                .abbreviation("ㅎ")
                .sequence(1)
                .deliverySite(deliverySite)
                .latitude(37.600326)
                .longitude(126.864485)
                .additionalTime(LocalTime.of(0,0,0))
                .build();
        DeliveryDetailSite detail2 = DeliveryDetailSite.builder()
                .name("기숙사 식당")
                .location("기숙사 내부 식당")
                .abbreviation("ㄱ")
                .sequence(2)
                .deliverySite(deliverySite)
                .latitude(37.598048)
                .longitude(126.866489)
                .additionalTime(LocalTime.of(0,5,0))
                .build();
        DeliveryDetailSite detail3 = DeliveryDetailSite.builder()
                .name("별관")
                .location("별관 식당")
                .abbreviation("ㄱ")
                .sequence(1)
                .deliverySite(deliverySite2)
                .latitude(37.598048)
                .longitude(126.866489)
                .additionalTime(LocalTime.of(0,0,0))
                .build();
        DeliveryDetailSite detail4 = DeliveryDetailSite.builder()
                .name("미디어관")
                .location("미디어 플레이스")
                .abbreviation("ㄴ")
                .sequence(2)
                .deliverySite(deliverySite2)
                .latitude(37.598048)
                .longitude(126.866489)
                .additionalTime(LocalTime.of(0,7,0))
                .build();
        detailJpaRepository.save(detail1);
        detailJpaRepository.save(detail2);
        detailJpaRepository.save(detail3);
        detailJpaRepository.save(detail4);




        FcmToken fcmToken1 = FcmToken.builder()
                .token("fcmToken1")
                .build();
        FcmToken fcmToken2 = FcmToken.builder()
                .token("fcmToken2")
                .build();
        FcmToken fcmToken3 = FcmToken.builder()
                .token("fcmToken3")
                .build();
        FcmToken fcmToken4 = FcmToken.builder()
                .token("fcmToken4")
                .build();

        Long fIdx1 = fcmTokenJpaRepository.save(fcmToken1).getIdx();
        Long fIdx2 = fcmTokenJpaRepository.save(fcmToken2).getIdx();
        Long fIdx3 = fcmTokenJpaRepository.save(fcmToken3).getIdx();
        Long fIdx4 = fcmTokenJpaRepository.save(fcmToken4).getIdx();

        PointRank pointRank1 = PointRank.builder()
                .level((short) 1)
                .title("평범한")
                .lowerLimitOrderCount(0)
                .lowerLimitRecommendCount(0)
                .percentSavePoint(5)
                .priceSavePoint(0)
                .rewardCouponPrice(500)
                .build();
        PointRank pointRank2 = PointRank.builder()
                .level((short) 2)
                .title("알뜰한")
                .lowerLimitOrderCount(10)
                .lowerLimitRecommendCount(1)
                .percentSavePoint(7)
                .priceSavePoint(0)
                .rewardCouponPrice(1000)
                .build();
        pointRankJpaRepository.save(pointRank1);
        pointRankJpaRepository.save(pointRank2);

        User user1 = User.builder()
                .deliveryDetailSite(detail1)
                .phoneNumber("01064784899")
                .password(Password.builder()
                        .value("1234")
                        .build())
                .name("최낙형")
                .nickname("낙지")
                .sex(Sex.MALE)
                .birth(LocalDate.parse("1993-01-10"))
                .idxFcmToken(fIdx1)
                .authorities("ROLE_USER,ROLE_STORE_OWNER")
                .build();
        User user2 = User.builder()
                .deliveryDetailSite(detail1)
                .phoneNumber("010-6478-4897")
                .password(Password.builder()
                        .value("1234")
                        .build())
                .name("김영희")
                .sex(Sex.FEMALE)
                .birth(LocalDate.parse("1993-01-11"))
                .isActive(false)
                .idxFcmToken(fIdx2)
                .build();
        User user3 = User.builder()
                .deliveryDetailSite(detail1)
                .phoneNumber("010-0000-0000")
                .password(Password.builder()
                        .value("1234")
                        .build())
                .name("디폴트")
                .sex(Sex.MALE)
                .birth(LocalDate.parse("1998-01-26"))
                .idxFcmToken(fIdx3)
                .build();
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

        // user input
        DeliveryDetailSiteDto ddsite = new DeliveryDetailSiteDto();
        ddsite.setIdx(1L);
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("01012345678");
        userDto.setPassword("1234");
        userDto.setName("테스트22");
        userDto.setNickname("테스트짱짱");
        userDto.setSex(Sex.FEMALE);
        userDto.setBirth(LocalDate.parse("1999-09-01"));
        userDto.setDeliveryDetailSite(ddsite);
        userDto.setIdxFcmToken(fIdx4);

        // save
        User user = userService.saveUser(userDto.toEntity());
        System.err.println("user : " + user);

        StoreCategory category1 = StoreCategory.builder()
                .categoryTitle("한식")
                .build();
        StoreCategory category2 = StoreCategory.builder()
                .categoryTitle("양식")
                .build();
        StoreCategory category3 = StoreCategory.builder()
                .categoryTitle("중식")
                .build();
        storeCategoryJpaRepository.save(category1);
        storeCategoryJpaRepository.save(category2);
        storeCategoryJpaRepository.save(category3);

        Store store1 = Store.builder()
                .storeInfo(StoreInfo.builder()
                        .name("맘스터치")
                        .description("엄마의 손맛, 수제햄버거 전문점 맘스터치ㅋ")
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)
                        .parallelProduction((short) 1)
                        .maximumProduction((short) 20)
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("09:00:00"))
                        .closeTime(LocalTime.parse("21:00:00"))
                        .isOpening(true)
                        .pauseDescription("임시휴무")
                        .build())
                .idxDeliverySite(deliverySite.getIdx())
                .avgStar(3.7f)
                .cntLike(178)
                .cntComment(54)
                .storeCategory(category2)
                .sequence(1)
                .build();
        Store store2 = Store.builder()
                .storeInfo(StoreInfo.builder()
                        .name("한솥도시락")
                        .description("싼맛! 싼마이 도시락! 한솥도시락ㅋㅋ")
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)
                        .parallelProduction((short) 1)
                        .maximumProduction((short) 20)
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("09:00:00"))
                        .closeTime(LocalTime.parse("21:00:00"))
                        .isOpening(true)
                        .pauseDescription("임시휴무")
                        .build())
                .idxDeliverySite(deliverySite.getIdx())
                .avgStar(4.2f)
                .cntLike(132)
                .cntComment(79)
                .storeCategory(category1)
                .sequence(2)
                .build();

        Store store3 = Store.builder()
                .storeInfo(StoreInfo.builder()
                        .name("맘스터치 성균관점")
                        .description("엄마의 손맛, 수제햄버거 전문점 맘스터치ㅋ")
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)
                        .parallelProduction((short) 1)
                        .maximumProduction((short) 20)
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("09:00:00"))
                        .closeTime(LocalTime.parse("21:00:00"))
                        .isOpening(true)
                        .pauseDescription("임시휴무")
                        .build())
                .idxDeliverySite(deliverySite2.getIdx())
                .avgStar(3.7f)
                .cntLike(178)
                .cntComment(54)
                .storeCategory(category2)
                .build();
        Store store4 = Store.builder()
                .storeInfo(StoreInfo.builder()
                        .name("한솥도시락 성균관점")
                        .description("싼맛! 싼마이 도시락! 한솥도시락ㅋㅋ")
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)
                        .parallelProduction((short) 1)
                        .maximumProduction((short) 20)
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("09:00:00"))
                        .closeTime(LocalTime.parse("21:00:00"))
                        .isOpening(true)
                        .pauseDescription("임시휴무")
                        .build())
                .idxDeliverySite(deliverySite2.getIdx())
                .avgStar(4.2f)
                .cntLike(132)
                .cntComment(79)
                .storeCategory(category1)
                .build();

        Store store5 = Store.builder()
                .storeInfo(StoreInfo.builder()
                        .name("피자매니")
                        .description("껍질치밥으로 유명한 피자매니란다 ㅎ")
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)
                        .parallelProduction((short) 1)
                        .maximumProduction((short) 20)
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("09:00:00"))
                        .closeTime(LocalTime.parse("21:00:00"))
                        .isOpening(true)
                        .pauseDescription("임시휴무")
                        .build())
                .idxDeliverySite(deliverySite.getIdx())
                .avgStar(4.2f)
                .cntLike(132)
                .cntComment(79)
                .storeCategory(category2)
                .sequence(3)
                .build();


        StoreImage storeBrandImage1 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/brand.png")
                .imageType(StoreImageType.BRAND)
                .build();
        StoreImage storeBrandImage2 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/2/brand.png")
                .imageType(StoreImageType.BRAND)
                .build();
        StoreImage storeBrandImage3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/3/brand.png")
                .imageType(StoreImageType.BRAND)
                .build();
        StoreImage storeBrandImage4 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/4/brand.png")
                .imageType(StoreImageType.BRAND)
                .build();
        StoreImage storeBrandImage5 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/5/brand.png")
                .imageType(StoreImageType.BRAND)
                .build();

        StoreImage storeImage1_1 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage1_2 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/2.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(1)
                .build();
        StoreImage storeImage1_3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/3.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(1)
                .build();
        StoreImage storeImage2_1 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/2/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage2_2 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/2/2.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(2)
                .build();
        StoreImage storeImage2_3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/2/3.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(3)
                .build();
        StoreImage storeImage3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/3/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage4_1 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/4/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage4_2 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/4/2.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(2)
                .build();
        StoreImage storeImage4_3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/4/3.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(3)
                .build();
        StoreImage storeImage5_1 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/5/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage5_2 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/5/2.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(2)
                .build();
        StoreImage storeImage5_3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/5/3.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(3)
                .build();

        store1.addImages(storeBrandImage1, storeImage1_1, storeImage1_2, storeImage1_3);
        store2.addImages(storeBrandImage2, storeImage2_1, storeImage2_2, storeImage2_3);
        store3.addImages(storeBrandImage3, storeImage3);
        store4.addImages(storeBrandImage4, storeImage4_1, storeImage4_2, storeImage4_3);
        store5.addImages(storeBrandImage5, storeImage5_1, storeImage5_2, storeImage5_3);

        // store's story
        StoreStory storeStory1 = StoreStory.builder()
                .title("고객리뷰")
                .build();
        storeStory1.addImages(
                StoreStoryImage.builder()
                    .imagePath(ImagePath.reviews(1L, 1L, 1L)+"1.jpg")
                    .sequence(1)
                    .build(),
                StoreStoryImage.builder()
                    .imagePath(ImagePath.reviews(1L, 1L, 1L)+"2.jpg")
                    .sequence(2)
                    .build()
        );
        store1.addStories(storeStory1);



        // store's product category
        ProductCategory productCategory1 = ProductCategory.builder()
                .categoryTitle("세트")
                .build();
        ProductCategory productCategory2 = ProductCategory.builder()
                .categoryTitle("단품")
                .build();

        ProductCategory productCategory3 = ProductCategory.builder()
                .categoryTitle("보울도시락")
                .build();
        ProductCategory productCategory4 = ProductCategory.builder()
                .categoryTitle("사각도시락")
                .build();
        ProductCategory productCategory5 = ProductCategory.builder()
                .categoryTitle("프리미엄")
                .build();
        ProductCategory productCategory6 = ProductCategory.builder()
                .categoryTitle("간식")
                .build();

        store1.addProductCategories(productCategory1, productCategory2);
        store2.addProductCategories(productCategory3, productCategory4, productCategory5, productCategory6);


        storeJpaRepository.save(store1);
        storeJpaRepository.save(store2);
        storeJpaRepository.save(store3);
        storeJpaRepository.save(store4);
        storeJpaRepository.save(store5);

        Product product1 = getProduct(1L,"싸이버거 세트",
                1L, 1L, 1, 6_000, 1_000, 500,
                1, 2, 3);
        Product product2 = getProduct(2L,"싸이버거",
                1L, 2L, 2, 4_000, 1_000, 500,
                1, 2, 3);
        Product product3 = getProduct(3L,"휠렛버거 세트",
                1L, 1L, 3, 5_500, 1_000, 500,
                1, 2, 3);
        Product product4 = getProduct(4L,"휠렛버거",
                1L, 2L, 4, 3_500, 1_000, 500,
                1, 2, 3);

        Product product2_1 = getProduct(5L,"갈비치킨마요",
                2L, 3L, 1, 3_200, 1_000, 300,
                1);
        Product product2_2 = getProduct(6L,"메가치킨마요",
                2L, 3L, 2, 5_500, 1_000, 300,
                1);
        Product product2_3 = getProduct(7L,"메가치킨제육",
                2L, 4L, 3, 6_900, 1_000, 300,
                1);
        Product product2_4 = getProduct(8L,"치킨마요",
                2L, 3L, 4, 2_900, 1_000, 300,
                1);
        Product product2_5 = getProduct(9L,"빅치킨마요",
                2L, 3L, 5, 3_500, 1_000, 300,
                1);
        Product product2_6 = getProduct(10L,"숯불직화구이 덮밥",
                2L, 5L, 6, 5_700, 1_000, 300,
                1);
        Product product2_7 = getProduct(11L,"왕치킨마요",
                2L, 3L, 7, 4_200, 1_000, 300,
                1);
        Product product2_8 = getProduct(12L,"한솥 철판볶음밥",
                2L, 4L, 8, 3_700, 1_000, 300,
                1);
        Product product2_9 = getProduct(13L,"돈까스 카레",
                2L, 3L, 9, 3_900, 1_000, 300,
                1);
        Product product2_10 = getProduct(14L,"불닭비빔밥",
                2L, 5L, 10, 4_500, 1_000, 300,
                1);
        Product product2_11 = getProduct(15L,"돈치마요",
                2L, 3L, 11, 3_500, 1_000, 300,
                1);


        productJpaRepository.save(product1);
        productJpaRepository.save(product2);
        productJpaRepository.save(product3);
        productJpaRepository.save(product4);

        productJpaRepository.save(product2_1);
        productJpaRepository.save(product2_2);
        productJpaRepository.save(product2_3);
        productJpaRepository.save(product2_4);
        productJpaRepository.save(product2_5);
        productJpaRepository.save(product2_6);
        productJpaRepository.save(product2_7);
        productJpaRepository.save(product2_8);
        productJpaRepository.save(product2_9);
        productJpaRepository.save(product2_10);
        productJpaRepository.save(product2_11);



        ProductSubCategory productSubCategory1 = ProductSubCategory.builder()
                .categoryTitle("맛 필수 선택")
                .build();
        ProductSubCategory productSubCategory2 = ProductSubCategory.builder()
                .categoryTitle("서브메뉴")
                .build();
        ProductSubCategory productSubCategory3 = ProductSubCategory.builder()
                .categoryTitle("음료")
                .build();
        productSubCategoryJpaRepository.save(productSubCategory1);
        productSubCategoryJpaRepository.save(productSubCategory2);
        productSubCategoryJpaRepository.save(productSubCategory3);

        ProductSub productSub1 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("착한맛")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory1)
                .productSubType(ProductSubType.RADIO)
                .sequence(1)
                .cost(Cost.builder()
                        .unitCost(0)
                        .build())
                .build();
        ProductSub productSub2 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("보통맛")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory1)
                .productSubType(ProductSubType.RADIO)
                .sequence(2)
                .cost(Cost.builder()
                        .unitCost(0)
                        .build())
                .build();
        ProductSub productSub3 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("매운맛")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory1)
                .productSubType(ProductSubType.RADIO)
                .sequence(3)
                .cost(Cost.builder()
                        .unitCost(0)
                        .build())
                .build();
        ProductSub productSub4 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("케이준양념감자")
                        .description("쫀딕쫀딕 케이준감자에 양념 뭍힌것이여~")
                        .subDescription("쪼끔 맵땅깨~")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory2)
                .productSubType(ProductSubType.NUMBER)
                .numberMinimum(0)
                .numberMaximum(10)
                .sequence(1)
                .cost(Cost.builder()
                        .unitCost(1600)
                        .build())
                .build();
        ProductSub productSub5 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("사이즈업")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory2)
                .productSubType(ProductSubType.CHECKBOX)
                .sequence(2)
                .cost(Cost.builder()
                        .unitCost(1000)
                        .build())
                .build();
        ProductSub productSub6 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("콜라")
                        .description("코카콜라여")
                        .subDescription("500L")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory3)
                .productSubType(ProductSubType.NUMBER)
                .numberMinimum(0)
                .sequence(1)
                .cost(Cost.builder()
                        .unitCost(1000)
                        .build())
                .build();
        ProductSub productSub7 = ProductSub.builder()
                .productSubInfo(ProductSubInfo.builder()
                        .name("사이다")
                        .description("사이다는 칠성이지ㅋ")
                        .subDescription("500L")
                        .build())
                .idxStore(1L)
                .productSubCategory(productSubCategory3)
                .productSubType(ProductSubType.NUMBER)
                .numberMinimum(0)
                .sequence(2)
                .cost(Cost.builder()
                        .unitCost(1000)
                        .build())
                .build();

        // /assets/images/subs/{idx-product-sub}/{idx-product-sub-image}.jpg
        ProductSubImage productSubImage1_1 = ProductSubImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/subs/4/1.jpg")
                .imageType(ProductSubImageType.MAIN)
                .sequence(1)
                .build();
        ProductSubImage productSubImage1_2 = ProductSubImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/subs/4/2.jpg")
                .imageType(ProductSubImageType.SUB)
                .sequence(2)
                .build();
        ProductSubImage productSubImage1_3 = ProductSubImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/subs/4/3.jpg")
                .imageType(ProductSubImageType.SUB)
                .sequence(3)
                .build();
        ProductSubImage productSubImage2 = ProductSubImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/subs/6/1.jpg")
                .imageType(ProductSubImageType.MAIN)
                .sequence(1)
                .build();
        ProductSubImage productSubImage3 = ProductSubImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/subs/7/1.jpg")
                .imageType(ProductSubImageType.MAIN)
                .sequence(1)
                .build();

        productSub4.addImage(productSubImage1_1);
        productSub4.addImage(productSubImage1_2);
        productSub4.addImage(productSubImage1_3);
        productSub6.addImage(productSubImage2);
        productSub7.addImage(productSubImage3);

        productSubJpaRepository.save(productSub1);
        productSubJpaRepository.save(productSub2);
        productSubJpaRepository.save(productSub3);
        productSubJpaRepository.save(productSub4);
        productSubJpaRepository.save(productSub5);
        productSubJpaRepository.save(productSub6);
        productSubJpaRepository.save(productSub7);


        ProductSubMapper mapper1 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub1)
                .build();
        ProductSubMapper mapper2 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub2)
                .build();
        ProductSubMapper mapper3 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub3)
                .build();
        ProductSubMapper mapper4 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub4)
                .build();
        ProductSubMapper mapper5 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub5)
                .build();
        ProductSubMapper mapper6 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub6)
                .build();
        ProductSubMapper mapper7 = ProductSubMapper.builder()
                .product(product1)
                .productSub(productSub7)
                .build();

        ProductSubMapper mapper8 = ProductSubMapper.builder()
                .product(product2)
                .productSub(productSub1)
                .build();
        ProductSubMapper mapper9 = ProductSubMapper.builder()
                .product(product2)
                .productSub(productSub2)
                .build();
        ProductSubMapper mapper10 = ProductSubMapper.builder()
                .product(product2)
                .productSub(productSub3)
                .build();

        productSubMapperJpaRepository.save(mapper1);
        productSubMapperJpaRepository.save(mapper2);
        productSubMapperJpaRepository.save(mapper3);
        productSubMapperJpaRepository.save(mapper4);
        productSubMapperJpaRepository.save(mapper5);
        productSubMapperJpaRepository.save(mapper6);
        productSubMapperJpaRepository.save(mapper7);
        productSubMapperJpaRepository.save(mapper8);
        productSubMapperJpaRepository.save(mapper9);
        productSubMapperJpaRepository.save(mapper10);


        // 항공대 시간표 1
        OrderTime orderTime1 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("11:30:00"))
                .pickUpTime(LocalTime.parse("11:45:00"))
                .arrivalTime(LocalTime.parse("12:00:00"))
                .build();
        OrderTime orderTime2 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("12:30:00"))
                .pickUpTime(LocalTime.parse("12:45:00"))
                .arrivalTime(LocalTime.parse("13:00:00"))
                .build();
        OrderTime orderTime3 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("17:30:00"))
                .pickUpTime(LocalTime.parse("17:45:00"))
                .arrivalTime(LocalTime.parse("18:00:00"))
                .build();
        OrderTime orderTime4 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("18:30:00"))
                .pickUpTime(LocalTime.parse("18:45:00"))
                .arrivalTime(LocalTime.parse("19:00:00"))
                .build();

        // 항공대 시간표 2
        OrderTime orderTime5 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("12:00:00"))
                .pickUpTime(LocalTime.parse("12:15:00"))
                .arrivalTime(LocalTime.parse("12:30:00"))
                .build();
        OrderTime orderTime6 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("13:00:00"))
                .pickUpTime(LocalTime.parse("13:15:00"))
                .arrivalTime(LocalTime.parse("13:30:00"))
                .build();
        OrderTime orderTime7 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("18:00:00"))
                .pickUpTime(LocalTime.parse("18:15:00"))
                .arrivalTime(LocalTime.parse("18:30:00"))
                .build();
        OrderTime orderTime8 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("19:00:00"))
                .pickUpTime(LocalTime.parse("19:15:00"))
                .arrivalTime(LocalTime.parse("19:30:00"))
                .build();

        // 성균관대 시간표 1
        OrderTime orderTime9 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("11:30:00"))
                .pickUpTime(LocalTime.parse("11:45:00"))
                .arrivalTime(LocalTime.parse("12:10:00"))
                .build();
        OrderTime orderTime10 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("12:30:00"))
                .pickUpTime(LocalTime.parse("12:45:00"))
                .arrivalTime(LocalTime.parse("13:10:00"))
                .build();
        OrderTime orderTime11 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("17:30:00"))
                .pickUpTime(LocalTime.parse("17:45:00"))
                .arrivalTime(LocalTime.parse("18:10:00"))
                .build();
        OrderTime orderTime12 = OrderTime.builder()
                .orderEndTime(LocalTime.parse("18:30:00"))
                .pickUpTime(LocalTime.parse("18:45:00"))
                .arrivalTime(LocalTime.parse("19:10:00"))
                .build();

        orderTimeJpaRepository.save(orderTime1);
        orderTimeJpaRepository.save(orderTime2);
        orderTimeJpaRepository.save(orderTime3);
        orderTimeJpaRepository.save(orderTime4);
        orderTimeJpaRepository.save(orderTime5);
        orderTimeJpaRepository.save(orderTime6);
        orderTimeJpaRepository.save(orderTime7);
        orderTimeJpaRepository.save(orderTime8);
        orderTimeJpaRepository.save(orderTime9);
        orderTimeJpaRepository.save(orderTime10);
        orderTimeJpaRepository.save(orderTime11);
        orderTimeJpaRepository.save(orderTime12);


        // store1 (항공대 맘스터치)
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime1).store(store1).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime2).store(store1).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime3).store(store1).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime4).store(store1).build());

        // store2 (항공대 한솥도시락)
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime1).store(store2).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime2).store(store2).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime3).store(store2).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime4).store(store2).build());

        // store5 (항공대 화전 피자매니)
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime5).store(store5).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime6).store(store5).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime7).store(store5).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime8).store(store5).build());

        // store3 (성균관대 맘스터치)
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime9).store(store3).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime10).store(store3).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime11).store(store3).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime12).store(store3).build());

        // store4 (성균관대 한솥도시락)
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime9).store(store4).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime10).store(store4).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime11).store(store4).build());
        orderTimeMapperJpaRepository.save(OrderTimeMapper.builder().orderTime(orderTime12).store(store4).build());




        Coupon coupon1 = Coupon.builder()
                .isUsed(false)
                .title("1,000원 할인쿠폰")
                .code("1XER-FGT3-1199")
                .beginDate(LocalDateTime.now())
                .discountCost(1_000)
                .user(user1)
                .build();
        coupon1 = couponJpaRepository.save(coupon1);

        Payment payment1 = Payment.builder()
                .paymentType(PaymentType.CREDIT_CARD)
                .build();

        paymentJpaRepository.save(payment1);


        // 주문
        orderJpaRepository.save(getOrder(1L, LocalDate.now(), 1L, payment1, OrderType.ORDER_READY,
                1L, 1L, 2));
        orderJpaRepository.save(getOrder(1L, LocalDate.now(), 2L, payment1, OrderType.ORDER_READY,
                1L, 1L, 1));
        orderJpaRepository.save(getOrder(1L, LocalDate.now(), 1L, payment1, OrderType.DELIVERY_READY,
                1L, 1L, 1));
        orderJpaRepository.save(getOrder(2L, LocalDate.now(), 1L, payment1, OrderType.ORDER_READY,
                1L, 1L, 2));
        orderJpaRepository.save(getOrder(2L, LocalDate.now(), 2L, payment1, OrderType.ORDER_READY,
                1L, 1L, 1));
        orderJpaRepository.save(getOrder(2L, LocalDate.now(), 1L, payment1, OrderType.DELIVERY_READY,
                1L, 1L, 1));
        orderJpaRepository.save(getOrder(3L, LocalDate.now(), 10L, payment1, OrderType.ORDER_READY,
                3L, 1L, 2));
        orderJpaRepository.save(getOrder(3L, LocalDate.now(), 10L, payment1, OrderType.DELIVERY_READY,
                4L, 1L, 1));
        orderJpaRepository.save(getOrder(3L, LocalDate.now(), 10L, payment1, OrderType.ORDER_READY,
                3L, 1L, 1));
        orderJpaRepository.save(getOrder(4L, LocalDate.now(), 11L, payment1, OrderType.DELIVERY_READY,
                3L, 1L, 2));
        orderJpaRepository.save(getOrder(4L, LocalDate.now(), 11L, payment1, OrderType.ORDER_READY,
                4L, 1L, 1));
        orderJpaRepository.save(getOrder(4L, LocalDate.now(), 10L, payment1, OrderType.DELIVERY_READY,
                3L, 1L, 1));

        // 주문 아이템(서브용) 생성
//        OrderItemSub orderItemSub1 = OrderItemSub.builder()
//                .productSub(ProductSub.builder().idx(1L).build())
//                .quantity((short) 1)
//                .build();
//        OrderItemSub orderItemSub2 = OrderItemSub.builder()
//                .productSub(ProductSub.builder().idx(6L).build())
//                .quantity((short) 2)
//                .build();

        // 주문 아이템(메인용) 생성
//        OrderItem orderItem1 = OrderItem.builder()
//                .store(store1)
//                .product(product1)
//                .quantity((short) 1)
//                .requirement("요구사항입니다. 피클빼주세요.")
//                .build();
//
//        orderItem1.addItem(orderItemSub1);
//        orderItem1.addItem(orderItemSub2);
//        order1.addItem(orderItem1);
//        orderJpaRepository.save(order1);

        CouponMapper couponMapper1 = CouponMapper.builder()
                .order(Order.builder().idx(1L).build())
                .coupon(coupon1)
                .build();
        couponMapperJpaRepository.save(couponMapper1);


//        OrderRequestDto oRequest1 = new OrderRequestDto();
//        oRequest1.setIdxOrderTime(1L);
//        oRequest1.setIdxDeliveryDetailSite(1L);
//        oRequest1.setOrderType(OrderType.PAYMENT_READY);
//        oRequest1.setBoxNumber((short) 2);
//        oRequest1.setOrdererType(OrdererType.USER);
//        oRequest1.setIdxFcmToken(1L);
//
//        User inputUser = userService.findByPhoneNumber("01064784899");
//        oRequest1.setUser(inputUser);
//        oRequest1.setIdxPayment(1L);
//        oRequest1.setUsingPoint(0);
//        oRequest1.setCashReceipt("546-05-11233");
//
//        Set<Long> idxesUsingCoupon = new HashSet<>();
//        idxesUsingCoupon.add(coupon1.getIdx());
//        oRequest1.setIdxesUsingCoupons(idxesUsingCoupon);
//
//        orderService.save(oRequest1);


//        String[] ff = {"배부른", "용감한", "갸냘픈", "가엾은", "굵은", "던지는", "마법사", "방금온", "브론즈", "마스터", "실버", "골드", "플레티넘", "완고한", "다이아", "감각적인", "가벼운", "잘생긴", "어여쁜"};
//        String[] ss = {"얼굴", "사마귀", "북극곰", "콜라", "아이폰", "향수", "꼬부기", "파이리", "롱스톤", "티모", "가렌", "마스터이", "언랭", "페이커", "포만이", "비타민", "발바닥", "손바닥", "지갑"};
//        for(int i=0; i< ff.length; i++) {
//            RandomNickname rnick = RandomNickname.builder()
//                    .first(ff[i])
//                    .second(ss[i])
//                    .build();
//            randomNicknameJpaRepository.save(rnick);
//        }
        //System.out.println(userService.findAll());
    }

    static int boxCnt = 0;
    private Order getOrder(Long ddIdx, LocalDate orderDate, Long oIdx, Payment payment, OrderType orderType, Long sIdx, Long pIdx, int quantity) {
        Order order = Order.builder()
                .boxNumber((short) boxCnt)
                .deliveryDetailSite(DeliveryDetailSite.builder().idx(ddIdx).build())
                .orderer(Orderer.builder()
                        .idxFcmToken(1L)
                        .ordererType(OrdererType.USER)
                        .user(User.builder().idx(1L).build())
                        .build())
                .orderDate(orderDate)
                .orderTime(OrderTime.builder().idx(oIdx).build())
                .orderType(orderType)
                .paymentInfo(PaymentInfo.builder()
                        .payment(payment)
                        .usingPoint(0)
                        .savedPoint(0)
                        .build())
                .build();
        OrderItem orderItem1 = OrderItem.builder()
                .store(Store.builder().idx(sIdx).build())
                .product(Product.builder().idx(pIdx).build())
                .quantity((short) quantity)
                .requirement("box : " + boxCnt + " orderTime : " + oIdx + " detail : " + ddIdx)
                .build();
        order.addItem(orderItem1);
        boxCnt++;
        return order;
    }

    private Product getProduct(Long idx, String name, Long sIdx, Long cIdx, int seq, int unitCost, int cf, int sf, int...idxesImage) {
        Product product = Product.builder()
                .productInfo(ProductInfo.builder()
                        .name(name)
                        .description(name+" 설명 부분입니다.")
                        .subDescription(name+" 부가설명 부분입니다.")
                        .build())
                .idxStore(sIdx)
                .productCategory(ProductCategory.builder().idx(cIdx).build())
                .sequence(seq)
                .cost(Cost.builder()
                        .unitCost(unitCost)
                        .priceClientFee(cf)
                        .priceStoreFee(sf)
                        .build())
                .build();

        for(int i=0; i<idxesImage.length; i++) {
            int idxImage = idxesImage[i];
            ProductImage productImage = ProductImage.builder()
                    .imagePath(ImagePath.products(1L, sIdx, idx)+idxImage+".jpg")
                    .imageType(i==0 ? ProductImageType.MAIN : ProductImageType.SUB)
                    .sequence(i+1)
                    .build();
            product.addImage(productImage);
        }

        return product;
    }
}
