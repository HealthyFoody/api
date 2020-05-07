package com.healthyfoody.entity;

import com.healthyfoody.validation.ValidUUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
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
