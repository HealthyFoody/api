package com.healthyfoody.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.COMBO)
public class Combo extends Product {

    @NotNull
    LocalDate fromDate;

    LocalDate toDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "combo")
    List<MealGroup> mealGroups;
}
