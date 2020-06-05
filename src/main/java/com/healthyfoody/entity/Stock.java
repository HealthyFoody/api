package com.healthyfoody.entity;

import java.time.LocalDateTime;

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
@IdClass(StockId.class)
@Table(name = TableName.STOCK)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Stock {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    Store store;

    @NotNull
    Boolean inStock;

    LocalDateTime restockedOn;

}
