package com.healthyfoody.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealResponse extends ProductResponse {
	private String calories;
	private List<String> ingredients;
}