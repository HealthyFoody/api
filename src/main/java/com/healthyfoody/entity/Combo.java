package com.healthyfoody.entity;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = TableName.COMBO)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Combo extends Product {

    @NotNull
    LocalDate fromDate;

    LocalDate toDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "combo")
    List<MealGroup> mealGroups;
}
