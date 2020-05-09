package com.healthyfoody.dto;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.validation.annotations.ValidUUID;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AddressRequest {

    @ValidUUID
    UUID id;

    String name;

    UUID customerId;

    @NotNull
    Double latitude;

    @NotNull
    Double longitude;

    @NotNull
    String line1;

    String line2;

    @NotNull
    String city;

    @NotNull
    String district;

    @NotNull
    String zipCode;

    @NotNull
    Boolean listed;

    Boolean defaultAddress;
}
