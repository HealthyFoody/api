package com.healthyfoody.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.MEAL)
public class Meal extends Product {

    @NotNull
    @Column(name = "calories_in_kcal")
    Integer calories;

    @NotNull
    String ingredients;
}
