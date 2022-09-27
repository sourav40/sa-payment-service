package edu.miu.cs590.paymentservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDto {

    private String bookingCode;
    private String paymentStatus;
}
