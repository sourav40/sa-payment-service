package edu.miu.cs590.paymentservice.serializer;

import edu.miu.cs590.paymentservice.dto.EmailDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class EmailSerializer implements Serializer<EmailDto> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(String topic, EmailDto emailDto) {
        int sizeOfName;
        int sizeOfDate;
        byte[] serializedName;
        byte[] serializedDate;
        int sizeOfFrom;
        int sizeOfTo;
        int sizeOfSubject;
        int sizeOfMessage;
        byte[] serializedFrom;
        byte[] serializedTo;
        byte[] serializedSubject;
        byte[] serializedMessage;

        try {
            if (emailDto == null)
                return null;

            serializedFrom = emailDto.getFrom().getBytes(encoding);
            sizeOfFrom = serializedFrom.length;
            serializedTo = emailDto.getTo().getBytes(encoding);
            sizeOfTo = serializedTo.length;
            serializedSubject = emailDto.getSubject().getBytes(encoding);
            sizeOfSubject = serializedSubject.length;
            serializedMessage = emailDto.getMessage().getBytes(encoding);
            sizeOfMessage = serializedMessage.length;

            ByteBuffer buf = ByteBuffer.allocate(4 + 4 + 4 + 4 + sizeOfFrom + 4 + 4 + 4 + sizeOfTo + 4 + 4 + sizeOfSubject + 4 + sizeOfMessage);
            buf.putInt(sizeOfFrom);
            buf.put(serializedFrom);
            buf.putInt(sizeOfTo);
            buf.put(serializedTo);
            buf.putInt(sizeOfSubject);
            buf.put(serializedSubject);
            buf.putInt(sizeOfMessage);
            buf.put(serializedMessage);

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

