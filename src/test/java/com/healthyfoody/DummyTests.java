package com.healthyfoody;

import com.healthyfoody.entity.Address;
import com.healthyfoody.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class DummyTests {
    @Test
    public void testEquals(){
        Address address1 = new Address();
        address1.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130003"));
        Address address2 = new Address();
        address1.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130002"));
        boolean response = address1.equals(address2);
        boolean expected = true;
        Assert.assertEquals(expected,response);
    }
}
