package com.healthyfoody.api.orders;

import com.healthyfoody.api.common.ValidUUID;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public class OrderForm {

    @ValidUUID
    UUID customerId;

    @ValidUUID
    UUID storeId;

    String programedFor;

    @Valid
    List<OrderDetailForm> products;
}
