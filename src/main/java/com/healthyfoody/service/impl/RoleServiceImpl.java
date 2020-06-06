package com.healthyfoody.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.entity.Role;
import com.healthyfoody.exception.InvalidOperationException;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.RoleRepository;
import com.healthyfoody.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findEntityById(Long id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role admin() {
		return findByName("Administrador");
	}

	@Override
	public Role customer() {
		return findByName("Cliente");
	}

	@Override
	public Role manager() {
		return findByName("Gerente");
	}

	@Override
	public Role courier() {
		return findByName("Courier");
	}

	@Override
	public Role getRole(Roles role) {
		switch(role) {
		case ADMINISTRATOR:
			return admin();
		case CUSTOMER:
			return customer();
		case MANAGER:
			return manager();
		case COURIER:
			return courier();
		default:
			throw new InvalidOperationException();
		}
	}

	@Override
	public Role findByName(String role) {
		return roleRepository.findByName(role).orElseThrow(() -> new ResourceNotFoundException(role, "nombre", Role.class));
	}
}
