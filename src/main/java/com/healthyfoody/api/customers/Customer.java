package com.healthyfoody.api.customers;

import javax.persistence.*;

import com.healthyfoody.api.users.UserAccount;
import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.CUSTOMER)
public class Customer extends BaseEntity {
	
	String firstName;
	
	String lastName;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id",unique = true, nullable = true)
    UserAccount user;
}
