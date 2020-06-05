package com.healthyfoody.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {
    @Id
    @Type(type = "uuid-char")
    @Column(updatable = false, nullable = false)
    @Builder.Default
    protected UUID id = UUID.randomUUID();
    
    public BaseEntity() {
    	this.id = UUID.randomUUID();
    }

}
