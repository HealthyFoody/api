package com.healthyfoody.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import com.healthyfoody.dto.response.BaseProductResponseDetails.*;
@Getter
@Setter
public class CartResponseDetails {
    String id;

    String customerId;

    BigDecimal totalPrice;

    List<MealItemDetailDto> meals;
}
