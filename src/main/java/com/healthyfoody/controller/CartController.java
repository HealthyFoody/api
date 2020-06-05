package com.healthyfoody.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.healthyfoody.dto.request.CartItemRequest;
import com.healthyfoody.entity.Combo;
import com.healthyfoody.service.CartService;
import com.healthyfoody.validation.annotations.GUID;
import com.healthyfoody.validation.groups.ProductGroups.OnCombos;
import com.healthyfoody.validation.groups.ProductGroups.OnMeals;

@Validated
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/")
    public ResponseEntity<?> requestCart(
            @GUID @RequestParam(name = "customer", required = false) String customerId) {
        return ResponseEntity.ok(cartService.obtainCustomerCart(UUID.fromString(customerId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(
            @PathVariable @GUID String id) {
        return ResponseEntity.ok(cartService.findById(UUID.fromString(id)));
    }

    @PostMapping("/{id}/meal/")
    @Validated({ OnMeals.class })
    public ResponseEntity<?> addMealToCart(
            @PathVariable @GUID String id,
            @RequestBody @Valid CartItemRequest mealRequest) {
        cartService.addToCart(UUID.fromString(id),
                mealRequest.getProductId(),
                mealRequest.getQuantity(),null,false );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/combo")
    @Validated({ Combo.class })
    public ResponseEntity<?> addComboToCart(
            @PathVariable @GUID String id,
            @RequestBody @Valid CartItemRequest comboRequest) {
        cartService.addToCart(UUID.fromString(id),
                comboRequest.getProductId(),
                comboRequest.getInstance(),
                comboRequest.getComponents(),
                false );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/meal")
    @Validated({ OnMeals.class })
    public ResponseEntity<?> editMealInCart(
            @PathVariable @GUID String id,
            @RequestBody @Valid CartItemRequest mealRequest) {
        cartService.addToCart(UUID.fromString(id),
                mealRequest.getProductId(),
                mealRequest.getQuantity(),null,true);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/combo")
    @Validated({ OnCombos.class })
    public ResponseEntity<?> editComboInCart(
            @PathVariable @GUID String id,
            @RequestBody @Valid CartItemRequest comboRequest) {
        cartService.addToCart(UUID.fromString(id),
                comboRequest.getProductId(),
                comboRequest.getInstance(),
                comboRequest.getComponents(),
                true );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removeItemFromCart(
            @PathVariable @GUID String id,
            @RequestBody @Valid CartItemRequest request) {
        cartService.deleteFromCart(UUID.fromString(id),
                request.getProductId(), request.getInstance());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/clear")
    public ResponseEntity<?> removeItemFromCart(
            @PathVariable @GUID String id) {
        cartService.clearCart(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
