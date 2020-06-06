package com.healthyfoody.service;

import com.healthyfoody.entity.Role;

public interface RoleService extends EntityFetchService<Role, Long> {
		
	Role admin();
	Role customer();
	Role manager();
	Role courier();
	
	Role getRole(Roles role);
	Role findByName(String role);

	public static enum Roles {
		ADMINISTRATOR,
		CUSTOMER,
		MANAGER,
		COURIER;
	}
}