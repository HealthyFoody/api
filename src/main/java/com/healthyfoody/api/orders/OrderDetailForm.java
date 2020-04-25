package com.healthyfoody.api.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.healthyfoody.api.common.ValidUUID;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailForm {
    @NotNull
    @ValidUUID
    private UUID productId;

    @Min(1)
    @Max(10)
    @NotNull
    private Integer quantity;

    private UUID comboId;

    private Integer bundledQuantity;
}
