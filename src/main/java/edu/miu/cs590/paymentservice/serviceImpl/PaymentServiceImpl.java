package edu.miu.cs590.paymentservice.serviceImpl;

import com.stripe.exception.StripeException;
import edu.miu.cs590.paymentservice.dto.CreatePaymentResponse;
import edu.miu.cs590.paymentservice.dto.PaymentRequestDto;
import edu.miu.cs590.paymentservice.dto.PaymentResponseDto;
import edu.miu.cs590.paymentservice.entity.Payment;
import edu.miu.cs590.paymentservice.kafka.EmailProducer;
import edu.miu.cs590.paymentservice.kafka.PaymentProducer;
import edu.miu.cs590.paymentservice.mapper.PaymentMapper;
import edu.miu.cs590.paymentservice.repository.PaymentRepository;
import edu.miu.cs590.paymentservice.service.PaymentService;
import edu.miu.cs590.paymentservice.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentProducer paymentProducer;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private EmailProducer emailProducer;

    @Override
    public CreatePaymentResponse savePaymentRequest(PaymentRequestDto paymentRequestDto) throws StripeException {
//        PaymentIntentCreateParams params =
//                PaymentIntentCreateParams.builder()
////                            .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
//                        .setAmount(15 * 100L) //stripe amount is always on cent
//                        .setCurrency("usd")
//                        .setAutomaticPaymentMethods(
//                                PaymentIntentCreateParams.AutomaticPaymentMethods
//                                        .builder()
//                                        .setEnabled(true)
//                                        .build()
//                        )
//                        .build();
//
//        Payment payment = paymentMapper.dtoToEntity(paymentRequestDto);
//        payment.setPaymentStatus(Constant.PENDING);
//        paymentRepository.save(payment);
//
//        // Create a PaymentIntent with the order amount and currency
//        PaymentIntent paymentIntent = PaymentIntent.create(params);
//        return new CreatePaymentResponse(paymentIntent.getClientSecret());
        return null;
    }

    @Override
    public void pushPaymentResponseToPaymentProducer(String token, String bookingCode, Double totalAmount, Double tax, String emailAddress) {

        Payment payment = Payment.builder()
                .token(token)
                .bookingCode(bookingCode)
                .totalAmount(totalAmount)
                .tax(tax)
                .emailAddress(emailAddress)
                .paymentStatus(Constant.SUCCESS)
                .paymentDate(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                .paymentStatus(Constant.SUCCESS)
                .bookingCode(bookingCode)
                .build();
        paymentProducer.producePaymentResponse(paymentResponseDto);

        PaymentRequestDto paymentRequestDto = paymentMapper.entityToDto(payment);
        emailProducer.producePaymentEmail(paymentRequestDto);
    }
}
