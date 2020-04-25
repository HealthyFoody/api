package com.healthyfoody.api.orders;

import com.healthyfoody.api.common.ValidUUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class OrderDetailForm {
    @NotNull
    @ValidUUID
    UUID product;

    @Min(1)
    @Max(10)
    @NotNull
    Integer quantity;

    UUID combo;

    Integer bundledQuantity;
}
