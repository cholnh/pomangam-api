package com.mrporter.pomangam.client.services.order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@RestClientTest(OrderServiceImpl.class)
public class OrderServiceImplTest {

    @MockBean
    private OrderServiceImpl orderService;

    @Test
    public void test() {
        int dMinute = (int) Duration.between(LocalTime.now(), LocalTime.parse("16:00:00")).toMinutes(); // 주문 마감까지 남은 시간
        System.out.println(dMinute);
        Assert.assertEquals(1, 2-1);
    }
}
