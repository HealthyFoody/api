package com.healthyfoody.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.request.UserRequest;
import com.healthyfoody.entity.AccountStatus;
import com.healthyfoody.entity.Role;
import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.exception.EmailExistsException;
import com.healthyfoody.exception.EmailNotFoundException;
import com.healthyfoody.repository.jpa.UserRepository;
import com.healthyfoody.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public void register(UserRequest user) {
		if (emailExists(user.getEmail())) {
			throw new EmailExistsException(user.getEmail());
		}
		UserAccount aux = new UserAccount();
		aux.setEmail(user.getEmail());
		aux.setPassword(passwordEncoder.encode(user.getPassword()));
		aux.setEmailValidated(true);
		aux.setStatusCode(AccountStatus.ACTIVE);
		aux.setRegisteredOn(LocalDateTime.now());
		Role role = new Role();
		role.setId(2L);

		aux.setRole(role);

		userRepository.save(aux);
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
