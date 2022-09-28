package edu.miu.cs590.paymentservice.service;

import com.stripe.exception.StripeException;
import edu.miu.cs590.paymentservice.dto.CreatePaymentResponse;
import edu.miu.cs590.paymentservice.dto.PaymentRequestDto;

public interface PaymentService {

    CreatePaymentResponse savePaymentRequest(PaymentRequestDto paymentRequestDto) throws StripeException;

    void pushPaymentResponseToPaymentProducer(String token, String bookingCode, Double totalAmount, Double tax, String emailAddress);
}
