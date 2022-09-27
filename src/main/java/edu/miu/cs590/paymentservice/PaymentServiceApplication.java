package edu.miu.cs590.paymentservice;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PaymentServiceApplication {

    @PostConstruct
    public void setup(){
        Stripe.apiKey="sk_test_51LlKiSGILNdVEOPLyf5r7ZeIMzkQPgp75LhNGuQEeFJ6HVxCef6vsjoJrZY5RGYWy98OqhhtaJpMBXUlgToksvAE00Vs5MQgd8";
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
