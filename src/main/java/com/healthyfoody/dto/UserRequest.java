package com.healthyfoody.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	@Size(min = 5)
	private String password;

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;

}