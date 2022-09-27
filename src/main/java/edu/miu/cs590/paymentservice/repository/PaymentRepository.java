package edu.miu.cs590.paymentservice.repository;

import edu.miu.cs590.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByBookingCode(String bookingCode);
}
