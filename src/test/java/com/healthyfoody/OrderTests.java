package com.healthyfoody;

import com.healthyfoody.service.impl.OrderServiceImpl;
import com.healthyfoody.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderTests {

    @InjectMocks
    OrderServiceImpl orderService;


    @Before
    public void beforeTests() {
        orderService = new OrderServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void nothing(){
        Assert.assertEquals(true,true);
    }
}
