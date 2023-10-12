package com.saga.payments.repositories;

import com.saga.payments.entities.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE payment p SET p.status = :status WHERE p.paymentId = :id")
    void cancelPayment(@Param(value = "id") Long id, @Param(value = "status") String status);
}
