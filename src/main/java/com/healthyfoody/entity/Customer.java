package com.healthyfoody.entity;

import java.util.UUID;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.CUSTOMER)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Customer extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", unique = true, nullable = true)
	UserAccount user;

	@Column(name = "current_cart")
	UUID currentCart;
}
