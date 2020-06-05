package com.healthyfoody.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {

	public EmailNotFoundException(String email) {
		super("No existe cuuenta asociada al email " + email);
	}

}
