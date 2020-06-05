package com.healthyfoody.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.TASK)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Task {

    @Id
    Long id;

    @NotNull
    @NaturalId
    @EqualsAndHashCode.Include
    Integer code;

    @NotNull
    @Column(unique = true)
    String name;
}
