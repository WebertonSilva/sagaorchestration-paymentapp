package com.saga.payments.services;


import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.entities.PaymentMapper;
import com.saga.payments.repositories.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    @Autowired
    private PaymentRepository paymentRepository;

    public String pay(Payment payment){
        payment.setStatus("paid");
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }

    @CircuitBreaker(name = "PaymentServices", fallbackMethod = "fallBackPayment")
    public PaymentDTO getPaymentById(String paymentId) {
        return PaymentMapper.mapToDTO(paymentRepository.findById(paymentId).get());
    }


    public PaymentDTO fallBackPayment(Throwable t){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setStatus("Error, Circuito Fechado!" + t.getMessage() + t.getStackTrace() + t.getCause());
        return paymentDTO;

    }

    public void cancelPaymentById(String id) {
        String status = "canceled";
        paymentRepository.cancelPayment(id, status);
    }

}
