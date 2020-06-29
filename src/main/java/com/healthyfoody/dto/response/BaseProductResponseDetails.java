package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class BaseProductResponseDetails implements ApiResponse{
        protected String id;
        protected String name;
        protected String imageUrl;
        protected String description;

        @Setter
        @Getter
        public static abstract class ItemDetailDto extends BaseProductResponseDetails {
            protected BigDecimal price;
        }

        @Setter
        @Getter
        public static class MealItemDetailDto extends ItemDetailDto {
            private Integer quantity;
        }

        @Setter
        @Getter
        public static class ComboItemDetailDto extends ItemDetailDto {
            private Integer instance;
            private List<ComponentDto> components;
        }

        @Setter
        @Getter
        public static class ComponentDto extends BaseProductResponseDetails {

        }
 }

