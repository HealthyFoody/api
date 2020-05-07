package com.healthyfoody.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.SALE_TIME_SPAN, uniqueConstraints = {@UniqueConstraint(columnNames = {"starting_hour", "ending_hour"})})
public class SaleTimeSpan extends BaseEntity {

    @NotNull
    String description;

    @Column(name = "starting_hour")
    LocalTime startingHour;

    @Column(name = "ending_hour")
    LocalTime endingHour;
}
