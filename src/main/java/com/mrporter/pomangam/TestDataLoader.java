package com.mrporter.pomangam;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSiteDto;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.repositories._bases.RandomNicknameJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.DeliverySiteJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.detail.DeliveryDetailJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.region.RegionJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalTime;

@Component
public class TestDataLoader implements ApplicationRunner {

    @Autowired StoreCategoryJpaRepository storeCategoryJpaRepository;
    @Autowired StoreJpaRepository storeJpaRepository;
    @Autowired DeliverySiteJpaRepository deliverySiteJpaRepository;
    @Autowired
    DeliveryDetailJpaRepository detailJpaRepository;
    @Autowired RegionJpaRepository regionJpaRepository;
    @Autowired RandomNicknameJpaRepository randomNicknameJpaRepository;

    @Autowired UserServiceImpl userService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(ApplicationArguments args) {
        if( !ddl.equals("create") ) return;

        run();
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

    void run() {
        Region region = Region.builder()
                .title("경기")
                .build();
        regionJpaRepository.save(region);

        DeliverySite deliverySite = DeliverySite.builder()
                .title("한국항공대")
                .region(region)
                .campus("본캠")
                .location("경기도 고양시 한국항공대학로 123")
                .build();
        DeliverySite deliverySite2 = DeliverySite.builder()
                .title("성균관대")
                .region(region)
                .campus("본캠")
                .location("경기도 화성시 성균관로 123")
                .build();
        deliverySiteJpaRepository.save(deliverySite);
        deliverySiteJpaRepository.save(deliverySite2);

        DeliveryDetailSite detail1 = DeliveryDetailSite.builder()
                .title("학생회관 뒤")
                .location("학생회관 뒤 족구장 있는 곳")
                .abbreviation("ㅎ")
                .sequence(1)
                .deliverySite(deliverySite)
                .latitude(37.600326)
                .longitude(126.864485)
                .additionalTime(LocalTime.of(0,0,0))
                .build();
        DeliveryDetailSite detail2 = DeliveryDetailSite.builder()
                .title("기숙사 식당")
                .location("기숙사 내부 식당")
                .abbreviation("ㄱ")
                .sequence(2)
                .deliverySite(deliverySite)
                .latitude(37.598048)
                .longitude(126.866489)
                .additionalTime(LocalTime.of(0,5,0))
                .build();
        DeliveryDetailSite detail3 = DeliveryDetailSite.builder()
                .title("별관")
                .location("별관 식당")
                .abbreviation("ㄱ")
                .sequence(1)
                .deliverySite(deliverySite2)
                .latitude(37.598048)
                .longitude(126.866489)
                .additionalTime(LocalTime.of(0,0,0))
                .build();
        DeliveryDetailSite detail4 = DeliveryDetailSite.builder()
                .title("미디어관")
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

        User user1 = User.builder()
                .deliveryDetailSite(detail1)
                .phoneNumber("01064784899")
                .password("1234")
                .name("최낙형")
                .nickname("낙지")
                .sex(Sex.MALE)
                .birth(Date.valueOf("1993-01-10"))
                .isActive(true)
                .build();
        User user2 = User.builder()
                .deliveryDetailSite(detail1)
                .phoneNumber("010-6478-4897")
                .password("1234")
                .name("김영희")
                .sex(Sex.FEMALE)
                .birth(Date.valueOf("1993-01-11"))
                .isActive(true)
                .build();
        User user3 = User.builder()
                .deliveryDetailSite(detail1)
                .phoneNumber("010-0000-0000")
                .password("1234")
                .name("디폴트")
                .build();
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

        // user input
        DeliveryDetailSiteDto ddsite = new DeliveryDetailSiteDto();
        ddsite.setIdx(1);
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("01012345678");
        userDto.setPassword("1234");
        userDto.setName("테스트22");
        userDto.setNickname("테스트짱짱");
        userDto.setDeliveryDetailSite(ddsite);

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
                .title("맘스터치")
                .deliverySite(deliverySite)
                .description("엄마의 손맛, 수제햄버거 전문점 맘스터치ㅋ")
                .avgStar(3.7f)
                .cntLike(178)
                .cntComment(54)
                .storeCategory(category2)
                .build();
        Store store2 = Store.builder()
                .title("한솥도시락")
                .deliverySite(deliverySite)
                .description("싼맛! 싼마이 도시락! 한솥도시락ㅋㅋ")
                .avgStar(4.2f)
                .cntLike(132)
                .cntComment(79)
                .storeCategory(category1)
                .build();

        Store store3 = Store.builder()
                .title("맘스터치 성균관점")
                .deliverySite(deliverySite2)
                .description("엄마의 손맛, 수제햄버거 전문점 맘스터치ㅋ")
                .avgStar(3.7f)
                .cntLike(178)
                .cntComment(54)
                .storeCategory(category2)
                .build();
        Store store4 = Store.builder()
                .title("한솥도시락 성균관점")
                .deliverySite(deliverySite2)
                .description("싼맛! 싼마이 도시락! 한솥도시락ㅋㅋ")
                .avgStar(4.2f)
                .cntLike(132)
                .cntComment(79)
                .storeCategory(category1)
                .build();
        storeJpaRepository.save(store1);
        storeJpaRepository.save(store2);
        storeJpaRepository.save(store3);
        storeJpaRepository.save(store4);

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
}
