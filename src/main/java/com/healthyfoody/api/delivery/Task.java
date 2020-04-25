package com.healthyfoody.api.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.TASK)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Task extends BaseEntity {
	
	@NotNull
	@NaturalId
	@Column(unique = true)
	@EqualsAndHashCode.Include
	String name;
}
