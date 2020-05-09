package com.healthyfoody.dto;

import com.healthyfoody.util.UuidUtil;
import com.healthyfoody.validation.groups.ComboItem;
import com.healthyfoody.validation.groups.MealItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartActionRequest {

    @Valid
    UUID productId;

    @Min(1)
    @NotNull(groups = { MealItem.class })
    Integer quantity;

    @Min(1)
    @NotNull(groups = { ComboItem.class })
    Integer instance;

    @NotEmpty(groups = { ComboItem.class })
    @Valid
    List<UuidUtil.Wrapper<UUID>> components;
}
