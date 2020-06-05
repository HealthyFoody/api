package com.healthyfoody.entity;

import java.time.LocalTime;

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
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = TableName.SALE_TIME_SPAN, uniqueConstraints = {@UniqueConstraint(columnNames = {"starting_hour", "ending_hour"})})
public class SaleTimeSpan extends BaseEntity {

    @NotNull
    String description;

    @Column(name = "starting_hour")
    LocalTime startingHour;

    @Column(name = "ending_hour")
    LocalTime endingHour;
}
