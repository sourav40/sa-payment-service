package edu.miu.cs590.paymentservice.controller;

import com.stripe.exception.StripeException;
import edu.miu.cs590.paymentservice.dto.CreatePaymentResponse;
import edu.miu.cs590.paymentservice.dto.PaymentRequestDto;
import edu.miu.cs590.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
        log.info("in this function to set up payment page.");
        return paymentService.savePaymentRequest(paymentRequestDto);
    }
}
