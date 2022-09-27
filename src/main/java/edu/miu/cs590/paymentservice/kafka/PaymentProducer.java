package edu.miu.cs590.paymentservice.kafka;

import edu.miu.cs590.paymentservice.dto.PaymentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
public class PaymentProducer {

    @Value("${kafka-server-url}")
    private String kafkaUrl;

    @Value("${kafka-server-port}")
    private String kafkaPort;

    public void producePaymentResponse(PaymentResponseDto paymentResponseDto) {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl + ":" + kafkaPort);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "edu.miu.cs590.paymentservice.serializer.PaymentResponseSerializer");

        KafkaProducer<String, PaymentResponseDto> producer = new KafkaProducer<>(properties);

        String topic = "payment_response";
        ProducerRecord<String, PaymentResponseDto> producerRecord =
                new ProducerRecord<>(topic, paymentResponseDto);

        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    log.info(
                            "Received new metadata \n" +
                                    "Topics: " + recordMetadata.topic() + "\n" +
                                    "Partition: " + recordMetadata.partition() + "\n" +
                                    "Offset: " + recordMetadata.offset() + "\n" +
                                    "Timestamp: " + recordMetadata.timestamp() + "\n"
                    );
                } else {
                    log.error("Error while producing", e);
                }
            }
        });

        producer.flush();

        producer.close();
    }
}

