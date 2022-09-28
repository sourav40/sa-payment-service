package edu.miu.cs590.paymentservice.controller;

import com.stripe.model.Charge;
import edu.miu.cs590.paymentservice.client.StripeClient;
import edu.miu.cs590.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentGatewayController {

    private StripeClient stripeClient;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader String token,
                             @RequestHeader String bookingCode,
                             @RequestHeader Double totalAmount,
                             @RequestHeader Double tax,
                             @RequestHeader String emailAddress) throws Exception {
        paymentService.pushPaymentResponseToPaymentProducer(token, bookingCode, totalAmount,tax,emailAddress);
        return stripeClient.chargeNewCard(token, totalAmount);
    }
}
