package com.healthyfoody.api.orders;

import com.healthyfoody.api.common.ValidUUID;
import org.springframework.hateoas.RepresentationModel;

public class OrderModel extends RepresentationModel<OrderModel> {
    @ValidUUID
	 String UUID;
}
