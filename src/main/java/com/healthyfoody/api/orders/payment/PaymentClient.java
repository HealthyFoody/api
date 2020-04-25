package com.healthyfoody.api.orders.payment;

import com.culqi.Culqi;
import com.culqi.util.CurrencyCode;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class PaymentClient {

    public static String publicKey = "pk_test_yp7aPFwTcm0jcQel";

    @Value("${culqi-secret-key}")
    public static String secretKey;

    public Map<String, Object> createCharge(Map<String, Object> token_created) throws Exception {
        Culqi culqi = new Culqi();
        culqi.public_key = publicKey;
        culqi.secret_key = secretKey;

        Map<String, Object> charge = new HashMap<>();
        Map<String, Object> antifraudDetails = new HashMap<String, Object>();

        antifraudDetails.put("address", "Calle Narciso de Colina 421 Miraflores");
        antifraudDetails.put("address_city", "LIMA");
        antifraudDetails.put("country_code", "PE");
        antifraudDetails.put("first_name", "Willy");
        antifraudDetails.put("last_name", "Aguirre");
        antifraudDetails.put("phone_number", "012767623");

        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "124");
        charge.put("amount",1000);
        charge.put("capture", true);
        charge.put("currency_code", CurrencyCode.PEN);
        charge.put("description","Venta de prueba");
        charge.put("email","test@culqi.com");
        charge.put("installments", 0);
        charge.put("antifraud_details", antifraudDetails);
        charge.put("metadata", metadata);
        charge.put("source_id", token_created.get("id").toString());

        Map<String, Object> charge_created = culqi.charge.create(charge);
        return charge_created;
    }

    public boolean captureCharge() {

        // TODO: implement
        return false;
    }
}
