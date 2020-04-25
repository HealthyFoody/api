package com.healthyfoody.api.product.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.COMBO)
public class Combo extends Product {
	
	@NotNull
	LocalDate fromDate;
	
	LocalDate toDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "combo")
	List<Group> groups;
}
