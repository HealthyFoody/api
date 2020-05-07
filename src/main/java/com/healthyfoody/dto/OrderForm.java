package com.healthyfoody.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.Order;
import com.healthyfoody.entity.Store;
import com.healthyfoody.util.ConverterUtil;
import com.healthyfoody.validation.ValidUUID;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderForm {

    @ValidUUID
    private UUID customerId;

    @ValidUUID
    private UUID storeId;

    private String address;

    private Double latitude;

    private Double longitude;

    private String programedFor;

    public static Order toEntity(OrderForm form) {
        Order order = new Order();

        Customer customer = new Customer();
        customer.setId(form.getCustomerId());
        order.setCustomer(customer);

        Store store = new Store();
        store.setId(form.getStoreId());
        order.setStore(store);

        order.setAddress(form.getAddress());
        order.setLatitude(form.getLatitude());
        order.setLongitude(form.getLongitude());

//        List<OrderProduct> details = form.getProducts().stream().map(item -> {
//            OrderProduct detail = new OrderProduct();
//            detail.setOrder(order);
//
//            Product product = new Product() { };
//            product.setId(item.getProductId());
//
//            detail.setProduct(product);
//            detail.setQuantity(item.getQuantity());
//
//            if (item.getComboId() != null){
//
//                Combo combo	= new Combo();
//                combo.setId(item.getComboId());
//                detail.setCombo(combo);
//
//                detail.setBundledQuantity(item.getBundledQuantity());
//            }
//
//            return detail;
//        }).collect(Collectors.toList());

        order.setOrderDate(LocalDateTime.now());

        LocalDateTime programmedDate = ConverterUtil.toLocalDateTime(form.getProgramedFor());
        order.setProgrammedFor(programmedDate == null ? order.getOrderDate(): programmedDate);

        return order;
    }
}
