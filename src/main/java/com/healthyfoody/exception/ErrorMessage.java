package com.healthyfoody.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorMessage implements Serializable {

	String error;
	String message;
}
