package com.healthyfoody.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComboResponse extends ProductResponse {
	private LocalDate fromDate;
	private LocalDate toDate;
	
	private List<ComboGroup> groups;
	

	@Setter
	@Getter
	public static class ComboGroup implements ApiResponse {
		private String name;
		private Boolean optional;
		
		List<ComponentDto> meals; 
	}
}
