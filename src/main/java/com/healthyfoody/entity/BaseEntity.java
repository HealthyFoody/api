package com.healthyfoody.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import com.healthyfoody.validation.annotations.ValidUUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {
    @Id
    @ValidUUID
    @Type(type = "uuid-char")
    @Column(updatable = false, nullable = false)
    protected UUID id;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }
}
