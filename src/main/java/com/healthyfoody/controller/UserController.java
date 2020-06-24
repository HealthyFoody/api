package com.healthyfoody.controller;

import javax.validation.Valid;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.validation.annotations.GUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import com.healthyfoody.config.security.JwtTokenUtil;
import com.healthyfoody.dto.request.LoginRequest;
import com.healthyfoody.dto.request.UserRequest;
import com.healthyfoody.dto.response.AuthResponse;
import com.healthyfoody.service.CustomerService;
import com.healthyfoody.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		
		authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		final String token = jwtTokenUtil.generateToken(userService.findEntityByEmail(loginRequest.getEmail()));

		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

		UUID userId = userService.register(userRequest);
		customerService.createCustomerProfile(userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> loadCustomerDetails(@PathVariable @GUID String customerId) {
		return ResponseEntity.ok(customerService.findById(UUID.fromString(customerId)));

	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
