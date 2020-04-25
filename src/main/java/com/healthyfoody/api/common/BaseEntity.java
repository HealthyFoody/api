package com.healthyfoody.api.common;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@ValidUUID
	@Type(type = "pg-uuid")
	@Column(updatable = false, nullable = false)
	protected UUID id;
	
	public BaseEntity() {
		this.id = UUID.randomUUID();
	}
}
