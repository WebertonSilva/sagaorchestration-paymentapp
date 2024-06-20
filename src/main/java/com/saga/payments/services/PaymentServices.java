package com.saga.payments.services;


import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.entities.PaymentMapper;
import com.saga.payments.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentServices {

    @Autowired
    PaymentRepository paymentRepository;

    public String pay(Payment payment){
        payment.setStatus("paid");
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }


    public PaymentDTO getPaymentById(String paymentId) {
        try {
            Payment payment = paymentRepository.findById(paymentId).get();
            return PaymentMapper.mapToDTO(payment);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void cancelPaymentById(String id) {
        String status = "canceled";
        paymentRepository.cancelPayment(id, status);
    }
}
