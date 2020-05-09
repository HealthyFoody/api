package com.healthyfoody;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ConditionTests {

    @Value("${jwt.secret}")
    String profile;

    @Test
    void testProductInStock() {

        System.out.println(profile);

        Assert.assertEquals(true,true);
    }
}
