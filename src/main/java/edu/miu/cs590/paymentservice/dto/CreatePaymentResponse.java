package edu.miu.cs590.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePaymentResponse {
    private String clientSecret;

    public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
