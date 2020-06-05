package com.healthyfoody.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.MEAL)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Meal extends Product {

    @NotNull
    @Column(name = "calories_in_kcal")
    Integer calories;

    @NotNull
    String ingredients;
    
    public List<String> getIngredients(){
		return Arrays.asList(this.ingredients.split(",")).stream()
				.map(String::trim).collect(Collectors.toList());
    }
}
