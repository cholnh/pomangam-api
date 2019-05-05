package com.mrporter.pomangam.feedbackHistory.likeForProduct.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "property.value=propertyTest",
                "value=test"
        },
        classes = {LikeForProductRepositoryImpl.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class LikeForProductTest {
}
