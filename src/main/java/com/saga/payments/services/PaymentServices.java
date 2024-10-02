package com.saga.payments.services;


import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.entities.PaymentMapper;
import com.saga.payments.repositories.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    @Autowired
    private PaymentRepository paymentRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(PaymentServices.class);

    @CircuitBreaker(name = "PaymentServices", fallbackMethod = "fbPay")
    public String pay(Payment payment){
        payment.setStatus("paid");
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }

    public String fbPay(Payment payment, Throwable t){
        LOGGER.error("Ocorreu uma exception : " + t.getCause());
        return null;
    }

    @CircuitBreaker(name = "PaymentServices", fallbackMethod = "fbGetPaymentById")
    public PaymentDTO getPaymentById(String paymentId) {
        return PaymentMapper.mapToDTO(paymentRepository.findById(paymentId).get());
    }

    public PaymentDTO fbGetPaymentById(String paymentId, Throwable t){
        LOGGER.error("Ocorreu uma exception : " + t.getCause());
        return null;
    }

    @CircuitBreaker(name = "PaymentServices", fallbackMethod = "fbCancelPaymentById")
    public void cancelPaymentById(String id) {
        String status = "canceled";
        paymentRepository.cancelPayment(id, status);
    }

    public String fbCancelPaymentById(String paymentId, Throwable t){
        LOGGER.error("Ocorreu uma exception : " + t.getCause());
        return null;
    }

}
