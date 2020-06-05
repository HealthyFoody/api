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
@Table(name = TableName.ROLE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Role {

    @Id
    Long id;

    @NotNull
    @NaturalId
    @EqualsAndHashCode.Include
    @Column(unique = true)
    String name;

    String description;
}
