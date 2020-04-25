package com.healthyfoody.api.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthyfoody.api.common.ValidUUID;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderForm {

    @ValidUUID
    private UUID customerId;

    @ValidUUID
    private UUID storeId;

    private String programedFor;

    @Valid
    private List<OrderDetailForm> products;
}
