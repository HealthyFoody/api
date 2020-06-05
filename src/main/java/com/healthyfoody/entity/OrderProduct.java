package com.healthyfoody.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.ORDER_PRODUCT)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class OrderProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @NotNull
    @Column(scale = 2, precision = 5)
    private BigDecimal price;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer instance;

    @NotNull
    private Boolean isCombo;

    @ElementCollection
    Set<OrderProductComponent> components;
}
