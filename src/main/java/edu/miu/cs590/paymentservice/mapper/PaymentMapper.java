package edu.miu.cs590.paymentservice.mapper;

import edu.miu.cs590.paymentservice.dto.PaymentRequestDto;
import edu.miu.cs590.paymentservice.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    Payment dtoToEntity(PaymentRequestDto paymentRequestDto);

    PaymentRequestDto entityToDto(Payment payment);
}
