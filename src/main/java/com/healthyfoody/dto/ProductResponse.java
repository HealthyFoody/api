package com.healthyfoody.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.healthyfoody.validation.annotations.ValidUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = MealModelDto.class, name = "meal"),
        @Type(value = ComboModelDto.class, name = "combo")
})
public class ProductResponse {

    @ValidUUID
    protected UUID id;

    protected String name;

    protected String description;

    @Positive
    protected BigDecimal price;

    protected Boolean listed;

    protected String saleStartsAt;

    protected String saleStopsAt;

    protected String imageUrl;

}