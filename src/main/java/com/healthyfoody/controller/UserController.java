package com.healthyfoody.controller;

import com.healthyfoody.config.JwtTokenUtil;
import com.healthyfoody.dto.UserRequest;
import com.healthyfoody.dto.AuthResponse;
import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.mapper.UserMapper;
import com.healthyfoody.service.CustomerService;
import com.healthyfoody.service.UserService;
import com.healthyfoody.validation.annotations.ValidUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
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

	@Autowired
	private UserMapper userMapper;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequest authenticationRequest) throws Exception {

		System.out.println("GAAAA");

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		UserAccount user = userService.findByEmail(authenticationRequest.getEmail());

		final UserDetails userDetails = userService
				.loadUserByUsername(user.getEmail());

		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", user.getId());

		final String token = jwtTokenUtil.generateToken(userDetails, claims);

		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest user) throws Exception {
		UserAccount userSaved = userService.register(userMapper.requestToEntity(user));
		if(userSaved.getId() !=  null){
			Customer customer = new  Customer();
			customer.setFirstName(user.getFirstName());
			customer.setLastName(user.getLastName());
			customer.setUser(userSaved);
			Customer insert = customerService.insert(customer);
		}
		return ResponseEntity.ok(userMapper.entityToResponse(userSaved));
	}
	@PostMapping("/customer/register")
	public ResponseEntity<?> saveCustomer(@RequestBody @Valid Customer customer){
		return ResponseEntity.ok(customerService.insert(customer));
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

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@RequestParam @ValidUUID String id) {
		UserAccount user = userService.findById(UUID.fromString(id));
		return ResponseEntity.ok(customerService.findCustomerByUser(user));
	}
}