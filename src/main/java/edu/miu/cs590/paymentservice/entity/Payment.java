package edu.miu.cs590.paymentservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingCode;
    private String paymentStatus;
    private LocalDateTime paymentDate;
    private double totalAmount;
    private double tax;
    private String emailAddress;

}
