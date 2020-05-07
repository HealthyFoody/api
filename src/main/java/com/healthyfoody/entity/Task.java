package com.healthyfoody.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.TASK)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @NaturalId
    @EqualsAndHashCode.Include
    Integer code;

    @NotNull
    @Column(unique = true)
    String name;
}
