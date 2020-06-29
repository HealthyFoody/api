package com.healthyfoody.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.healthyfoody.dto.response.BaseProductResponse;
import com.healthyfoody.dto.response.BaseProductResponseDetails;
import com.healthyfoody.dto.response.CartResponse;
import com.healthyfoody.dto.response.CartResponseDetails;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.service.ProductService;
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

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> requestCart(
            @GUID @RequestParam(name = "customer", required = false) String customerId) {
        return ResponseEntity.ok(cartService.obtainCustomerCart(UUID.fromString(customerId)));
    }

    @GetMapping("/details")
    public ResponseEntity<?> requestCartDetails(@GUID @RequestParam(name="customer",required = true)String customerId){
        CartResponse cart = cartService.obtainCustomerCart(UUID.fromString(customerId));
        CartResponseDetails cartDetails = new CartResponseDetails();
        cartDetails.setId(cart.getId());
        cartDetails.setCustomerId(cart.getCustomerId());
        if(!cart.getMeals().isEmpty()){
            List<BaseProductResponseDetails.MealItemDetailDto> meals = new ArrayList<>();
            BigDecimal totalPrice = BigDecimal.valueOf(0.00);

            for(BaseProductResponse.MealItemDto item : cart.getMeals()){
                BaseProductResponseDetails.MealItemDetailDto mealDetail = new BaseProductResponseDetails.MealItemDetailDto();
                mealDetail.setQuantity(item.getQuantity());
                mealDetail.setId(item.getId());

                Meal meal = productService.findMealEntityById(UUID.fromString(item.getId()));
                mealDetail.setImageUrl(meal.getImageUrl());
                mealDetail.setName(meal.getName());
                mealDetail.setPrice(meal.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                totalPrice = totalPrice.add(mealDetail.getPrice());
                mealDetail.setDescription(meal.getDescription());
                meals.add(mealDetail);
            }
            cartDetails.setTotalPrice(totalPrice);
            cartDetails.setMeals(meals);
        }

        return ResponseEntity.ok(cartDetails);
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
        return ResponseEntity.ok(cartService.addToCart(UUID.fromString(id),
                mealRequest.getProductId(),
                mealRequest.getQuantity(),null,true ));
    }

    @PostMapping("/{id}/combo")
    @Validated({ Combo.class })
    public ResponseEntity<?> addComboToCart(
            @PathVariable @GUID String id,
            @RequestBody @Valid CartItemRequest comboRequest) {

        return ResponseEntity.ok(cartService.addToCart(UUID.fromString(id),
                comboRequest.getProductId(),
                comboRequest.getInstance(),
                comboRequest.getComponents(),
                true ));
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
        return ResponseEntity.ok(cartService.deleteFromCart(UUID.fromString(id),
                request.getProductId(), request.getInstance()));
    }

    @DeleteMapping("/{id}/clear")
    public ResponseEntity<?> removeItemFromCart(
            @PathVariable @GUID String id) {
        cartService.clearCart(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
