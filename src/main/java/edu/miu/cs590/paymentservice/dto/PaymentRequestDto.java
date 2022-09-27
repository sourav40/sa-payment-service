package edu.miu.cs590.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private String bookingCode;
    private double tax;
    private double totalAmount;
    private String emailAddress;
}
