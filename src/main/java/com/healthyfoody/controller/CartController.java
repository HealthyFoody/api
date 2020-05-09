package com.healthyfoody.controller;

import com.healthyfoody.dto.CartActionRequest;
import com.healthyfoody.entity.Combo;
import com.healthyfoody.service.CartService;
import com.healthyfoody.util.UuidUtil;
import com.healthyfoody.validation.annotations.ValidUUID;
import com.healthyfoody.validation.groups.ComboItem;
import com.healthyfoody.validation.groups.MealItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/")
    public ResponseEntity<?> requestCart(
            @ValidUUID @RequestParam(name = "customer", required = false) String customerId) {
        return ResponseEntity.ok(cartService.createCart(UUID.fromString(customerId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(
            @PathVariable @ValidUUID String id) {
        return ResponseEntity.ok(cartService.findById(UUID.fromString(id)));
    }

    @PostMapping("/{id}/meal/")
    @Validated({ MealItem.class })
    public ResponseEntity<?> addMealToCart(
            @PathVariable @ValidUUID String id,
            @RequestBody @Valid CartActionRequest mealRequest) {
        cartService.addToCart(UUID.fromString(id),
                mealRequest.getProductId(),
                mealRequest.getQuantity(),null,false );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/combo")
    @Validated({ Combo.class })
    public ResponseEntity<?> addComboToCart(
            @PathVariable @ValidUUID String id,
            @RequestBody @Valid CartActionRequest comboRequest) {
        cartService.addToCart(UUID.fromString(id),
                comboRequest.getProductId(),
                comboRequest.getInstance(),
                comboRequest.getComponents().stream().map(UuidUtil.Wrapper::value).collect(Collectors.toList()),
                false );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/meal")
    @Validated({ MealItem.class })
    public ResponseEntity<?> editMealInCart(
            @PathVariable @ValidUUID String id,
            @RequestBody @Valid CartActionRequest mealRequest) {
        cartService.addToCart(UUID.fromString(id),
                mealRequest.getProductId(),
                mealRequest.getQuantity(),null,true);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/combo")
    @Validated({ ComboItem.class })
    public ResponseEntity<?> editComboInCart(
            @PathVariable @ValidUUID String id,
            @RequestBody @Valid CartActionRequest comboRequest) {
        cartService.addToCart(UUID.fromString(id),
                comboRequest.getProductId(),
                comboRequest.getInstance(),
                comboRequest.getComponents().stream().map(UuidUtil.Wrapper::value).collect(Collectors.toList()),
                true );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removeItemFromCart(
            @PathVariable @ValidUUID String id,
            @RequestBody @Valid CartActionRequest request) {
        cartService.deleteFromCart(UUID.fromString(id),
                request.getProductId(), request.getInstance());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/clear")
    public ResponseEntity<?> removeItemFromCart(
            @PathVariable @ValidUUID String id) {
        cartService.clearCart(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
