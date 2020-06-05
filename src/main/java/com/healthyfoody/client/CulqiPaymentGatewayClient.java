package com.healthyfoody.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.culqi.Culqi;
import com.culqi.util.CurrencyCode;
import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.Order;

@Component
public class CulqiPaymentGatewayClient implements PaymentGatewayClient, CardStorageClient {

	@Value("${culqi.api.pk:placeholder}")
	private String publicKey;

	@Value("${culqi.api.sk:placeholder}")
	private String secretKey;

	public CulqiPaymentGatewayClient() {
		Culqi.public_key = publicKey;
		Culqi.secret_key = secretKey;
	}

	@Override
	public Map<String, Object> makePayment(Order order, Map<String, Object> token) throws Exception {
		Culqi culqi = new Culqi();

		Map<String, Object> charge = new HashMap<>();
		Map<String, Object> antifraudDetails = new HashMap<String, Object>();

		// TODO: FIX MAPPING
		Customer customer = order.getCustomer();

		antifraudDetails.put("address", order.getAddress());
		antifraudDetails.put("first_name", customer.getUser().getFirstName());
		antifraudDetails.put("last_name", customer.getUser().getLastName());

		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("oder_id", order.getId().toString());
		metadata.put("customer_id", customer.getId());

		charge.put("source_id", token.get("id").toString());
		charge.put("amount", order.getPrice().movePointRight(2).longValue());
		charge.put("currency_code", CurrencyCode.PEN);
		charge.put("email", customer.getUser().getEmail());
		charge.put("capture", true);
		charge.put("installments", 0);
		charge.put("description", "Venta de prueba");

		charge.put("antifraud_details", antifraudDetails);
		charge.put("metadata", metadata);

		return culqi.charge.create(charge);
	}

	@Override
	public Map<String, Object> refundPayment(Order order) {
		return null;
	}

	@Override
	public Map<String, Object> storeCard() {
		return null;
	}

	@Override
	public Map<String, Object> getCards() {
		return null;
	}
}
