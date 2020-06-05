package com.healthyfoody.entity;

import java.time.LocalDateTime;
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
@IdClass(TrackingId.class)
@Table(name = TableName.TRACKING)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Tracking {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    Task task;

    @NotNull
    Integer stepNumber;

    LocalTime estimatedTime;

    LocalDateTime finishTime;

}
