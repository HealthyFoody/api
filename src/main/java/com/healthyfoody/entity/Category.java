package com.healthyfoody.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.CATEGORY)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Category extends BaseEntity {

    @NotNull
    @Column(unique = true)
    String name;

    String description;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = TableName.CATEGORY_PRODUCT,
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    List<Product> products;

    String imageUrl;
}
