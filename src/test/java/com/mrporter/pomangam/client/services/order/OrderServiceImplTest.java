package com.mrporter.pomangam.client.services.order;

import com.mrporter.pomangam.client.domains.order.Order;
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
        System.out.println(isValidAuthorities(null));                       // false
        System.out.println(isValidAuthorities(""));                         // false
        System.out.println(isValidAuthorities("ROLE_"));                    // false
        System.out.println(isValidAuthorities("role_aa"));                  // true
        System.out.println(isValidAuthorities("ROLE_USER"));                // true
        System.out.println(isValidAuthorities("ROLE_USER,ROLS_SSS"));       // false
        System.out.println(isValidAuthorities("ROLE_USER,ROLE_ADMIN"));     // true

        Assert.assertEquals(1, 2-1);
    }

    private boolean isValidAuthorities(String authorities) {
        try {
            if(authorities == null || authorities.isEmpty()) return false;
            for(String authority : authorities.split(",")) {
                if(authority.length() < 6 ||
                        !authority.toUpperCase().substring(0, 5).equals("ROLE_")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
