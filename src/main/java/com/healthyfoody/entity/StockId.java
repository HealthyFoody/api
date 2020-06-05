package com.healthyfoody.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

@Data
public class StockId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Type(type = "uuid-char")
    UUID product;

    @Type(type = "uuid-char")
    UUID store;
}
