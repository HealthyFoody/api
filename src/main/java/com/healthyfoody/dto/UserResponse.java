package com.healthyfoody.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.healthyfoody.entity.AccountStatus;
import com.healthyfoody.validation.annotations.ValidUUID;
import com.healthyfoody.validation.groups.Modify;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	String email;
	String password;
}
