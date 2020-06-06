package com.healthyfoody.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.request.UserRequest;
import com.healthyfoody.entity.AccountStatus;
import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.exception.EmailExistsException;
import com.healthyfoody.exception.EmailNotFoundException;
import com.healthyfoody.repository.jpa.UserRepository;
import com.healthyfoody.service.RoleService;
import com.healthyfoody.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roles;
	
	@Override
	@Transactional
	public UUID register(UserRequest user) {
		if (emailExists(user.getEmail())) {
			throw new EmailExistsException(user.getEmail());
		}
		UserAccount aux = new UserAccount();
		aux.setEmail(user.getEmail());
		aux.setPassword(passwordEncoder.encode(user.getPassword()));
		aux.setEmailValidated(true);
		aux.setStatusCode(AccountStatus.ACTIVE);
		aux.setRegisteredOn(LocalDateTime.now());
		aux.setRole(roles.customer());

		return userRepository.save(aux).getId();
	}

	public Boolean emailExists(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserAccount user = findEntityByEmail(email);
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
	
	@Override
	public UserAccount findEntityByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
	}
}
