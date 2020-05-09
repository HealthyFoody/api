package com.healthyfoody.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.healthyfoody.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.UserMapper;
import com.healthyfoody.repository.jpa.UserRepository;
import com.healthyfoody.service.UserService;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserAccount register(UserAccount user) {
		if (emailExists(user.getEmail())) {
			throw new EmailExistsException();
		}
		UserAccount aux = new UserAccount();
		aux.setEmail(user.getEmail());
		aux.setPassword(passwordEncoder.encode(user.getPassword()));
		aux.setRegisteredOn(LocalDateTime.now());
		aux.setRole(user.getRole());

		return userRepository.save(aux);
	}

	public Boolean emailExists(String email) {
		return userRepository.existsByEmail(email);
	}

    @Override
    public UserAccount findByEmail(String email) {

		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
    }

    @Override
	public UserAccount findById(UUID id) throws ResourceNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, UserAccount.class));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserAccount user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(""));
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
}
