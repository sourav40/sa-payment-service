package edu.miu.cs590.paymentservice.client;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51LlKiSGILNdVEOPLyf5r7ZeIMzkQPgp75LhNGuQEeFJ6HVxCef6vsjoJrZY5RGYWy98OqhhtaJpMBXUlgToksvAE00Vs5MQgd8";
    }

    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }

    public Charge chargeNewCard(String token, double amount) throws Exception {
        try{
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int)(amount * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("source", token);
            Charge charge = Charge.create(chargeParams);

            return charge;
        }catch (Exception e){
            System.out.println("in excetpion");

            return null;
        }
    }

    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
