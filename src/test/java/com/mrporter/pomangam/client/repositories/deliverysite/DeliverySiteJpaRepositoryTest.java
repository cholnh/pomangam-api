package com.mrporter.pomangam.client.repositories.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner.class)
public class DeliverySiteJpaRepositoryTest {

    @Autowired
    DeliverySiteJpaRepository deliverySiteJpaRepository;

//    @Test
//    public void 배달지_N1_문제_테스트() {
//        List<DeliverySite> deliverySites = deliverySiteJpaRepository.findAllByIsActiveIsTrue(PageRequest.of(0, 10)).getContent();
//        for(DeliverySite deliverySite : deliverySites) {
//            List<DeliveryDetailSite> details = deliverySite.getDetailSites();
//            System.out.println(details); // -> N+1 발생
//        }
//    }
//
//    @Test
//    public void 배달지_N1_문제_해결_테스트() {
//        List<DeliverySite> deliverySites = deliverySiteJpaRepository.findAllFetchJoinByIsActiveIsTrue(PageRequest.of(0, 10)).getContent();
//        for(DeliverySite deliverySite : deliverySites) {
//            List<DeliveryDetailSite> details = deliverySite.getDetailSites();
//            System.out.println(details); // fetch join 으로 N+1 문제 해결
//        }
//    }
}
