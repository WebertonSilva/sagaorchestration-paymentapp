package com.saga.payments.services;


import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.entities.PaymentMapper;
import com.saga.payments.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    @Autowired
    PaymentRepository paymentRepository;

    public Long pay(Payment payment){
        payment.setStatus("paid");
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }


    public PaymentDTO getPaymentById(Long paymentId){
        return PaymentMapper.mapToDTO(paymentRepository.findById(paymentId).get());
    }

    public void cancelPaymentById(Long id) {
        String status = "canceled";
        paymentRepository.cancelPayment(id, status);
    }
}
