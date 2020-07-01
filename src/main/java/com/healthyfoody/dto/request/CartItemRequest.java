package com.healthyfoody.dto.request;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.*;

import com.healthyfoody.dto.ApiRequest;
import com.healthyfoody.validation.annotations.GUID;
import com.healthyfoody.validation.groups.ProductGroups.OnCombos;
import com.healthyfoody.validation.groups.ProductGroups.OnMeals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest implements ApiRequest {

    @GUID
    UUID productId;
    
    @NotNull
    String type;

    @Null(groups = { OnCombos.class })
    @Min(value = 1, groups = { OnMeals.class })
    @NotNull(groups = { OnMeals.class })
    Integer quantity;

    @Null(groups = { OnMeals.class })
    @Min(value = 1, groups = { OnCombos.class })
    @NotNull(groups = { OnCombos.class })
    Integer instance;

    @Null(groups = { OnMeals.class })
    @NotEmpty(groups = { OnCombos.class })
    List<UUID> components;
    
    public CartItemRequest() { }
    
    public CartItemRequest(UUID productId, Integer quantity) {
    	this.productId = productId;
    	this.quantity = quantity;
    	this.type = "meal";
    }
    
    public CartItemRequest(UUID productId, Integer instance, List<UUID> components) {
    	this.productId = productId;
    	this.instance = instance;
    	this.components = components;
    	this.type = "combo";
    }
}
