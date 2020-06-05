package com.healthyfoody.entity;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
public class TrackingId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Type(type = "uuid-char")
    UUID order;

    Long task;
}
