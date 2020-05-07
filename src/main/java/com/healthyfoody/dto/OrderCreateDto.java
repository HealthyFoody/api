package com.healthyfoody.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderCreateDto {

    UUID customerId;

    UUID storeId;

    UUID cartId;

    Double latitude;

    Double longitude;

    String address;

    LocalDateTime preparedFor;
}
