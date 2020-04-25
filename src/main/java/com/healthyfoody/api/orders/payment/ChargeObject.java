package com.healthyfoody.api.orders.payment;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ChargeObject {

    BigDecimal amount;
    String currencyCode;
    String email;
    String sourceId;
    Boolean capture;
    String description;
    Integer installments;
}
