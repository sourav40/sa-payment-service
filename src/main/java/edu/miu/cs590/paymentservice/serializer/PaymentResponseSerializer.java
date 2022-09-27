package edu.miu.cs590.paymentservice.serializer;

import edu.miu.cs590.paymentservice.dto.PaymentResponseDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class PaymentResponseSerializer implements Serializer<PaymentResponseDto> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(String topic, PaymentResponseDto paymentRequestDto) {

        int sizeOfBookingCode;
        int sizeOfPaymentStatus;
        byte[] serializedBookingCode;
        byte[] serializedPaymentStatus;

        try {
            if (paymentRequestDto == null)
                return null;

            serializedBookingCode = paymentRequestDto.getBookingCode().getBytes(encoding);
            sizeOfBookingCode = serializedBookingCode.length;
            serializedPaymentStatus = paymentRequestDto.getPaymentStatus().getBytes(encoding);
            sizeOfPaymentStatus = serializedPaymentStatus.length;

            ByteBuffer buf = ByteBuffer.allocate(4 + 4 + sizeOfBookingCode + 4 + sizeOfPaymentStatus);
            buf.putInt(sizeOfBookingCode);
            buf.put(serializedBookingCode);
            buf.putInt(sizeOfPaymentStatus);
            buf.put(serializedPaymentStatus);

            return buf.array();

        } catch (Exception e) {
            throw new SerializationException("Error when serializing Supplier to byte[]");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}

