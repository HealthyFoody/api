package com.healthyfoody.dto.request;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.healthyfoody.dto.ApiRequest;
import com.healthyfoody.util.GUIDWrapper;
import com.healthyfoody.validation.annotations.GUID;
import com.healthyfoody.validation.groups.ProductGroups.OnCombos;
import com.healthyfoody.validation.groups.ProductGroups.OnMeals;

import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    List<@Valid GUIDWrapper> components;

    void setComponents(List<UUID> components) {
        this.components = new ArrayList<>();
        for (UUID id: components) {
            this.components.add(new GUIDWrapper(id));
        }
    }
    
    public CartItemRequest() { }
    
    public CartItemRequest(UUID productId, Integer quantity) {
    	this.productId = productId;
    	this.quantity = quantity;
    	this.type = "meal";
    }
    
    public CartItemRequest(UUID productId, Integer instance, List<UUID> components) {
    	this.productId = productId;
    	this.instance = instance;
    	this.setComponents(components);
    	this.type = "combo";
    }
}
