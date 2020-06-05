package com.healthyfoody.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseProductResponse implements ApiResponse {
	protected String id;
	protected String name;

	@Setter
	@Getter
	public static abstract class ItemDto extends BaseProductResponse {
		protected BigDecimal price;
	}

	@Setter
	@Getter
	public static class MealItemDto extends ItemDto {
		private Integer quantity;
	}

	@Setter
	@Getter
	public static class ComboItemDto extends ItemDto {
		private Integer instance;
		private List<ComponentDto> components;
	}

	@Setter
	@Getter
	public static class ComponentDto extends BaseProductResponse {

	}

}
