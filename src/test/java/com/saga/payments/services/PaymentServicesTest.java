package com.saga.payments.services;


import com.saga.payments.entities.CreditCard;
import com.saga.payments.entities.CreditCardDTO;
import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

//@WebMvcTest(AppController.class)
//@AutoConfigureMockMvc()
@SpringBootTest(classes = PaymentServicesTest.class)
public class PaymentServicesTest {

    @InjectMocks
    PaymentServices paymentService;

    @Mock
    PaymentRepository mockPaymentRepository;


    @BeforeEach
    public void beforeEach() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPayOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.save(payment)).thenReturn(payment);

        String idPayment = paymentService.pay(payment);
        assertEquals(idPayment, "10");
    }

    @Test
    public void testPayNOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.save(payment)).thenReturn(payment);

        String idPayment = paymentService.pay(payment);
        assertNotEquals(idPayment, "1");
    }

    @Test
    public void testGetPaymentByIdOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.findById("10")).thenReturn(Optional.of(payment));

        PaymentDTO paymentDTO = paymentService.getPaymentById("10");
        assertEquals(paymentDTO.getPaymentId(), "10");
    }

    @Test
    public void testGetPaymentByIdNOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.findById("10")).thenReturn(Optional.of(payment));

        PaymentDTO paymentDTO = paymentService.getPaymentById("10");
        assertNotEquals(paymentDTO.getPaymentId(), "1");
    }

    @Test
    public void testcancelPaymentByIdOk(){

        doNothing().when(mockPaymentRepository).cancelPayment("10", "canceled");
        paymentService.cancelPaymentById("10");

    }


    private PaymentDTO getPaymentDTO() {

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setCardHolderName("JOAO DA SILVA");
        creditCardDTO.setNumber("1234567890123456");
        creditCardDTO.setCvvCode("123");
        creditCardDTO.setExpirationDate(LocalDate.parse("2030-01-01"));

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId("1");
        paymentDTO.setPaymentType("CREDIT CARD");
        paymentDTO.setStatus("PAID");
        paymentDTO.setPaymentValue(350D);

        paymentDTO.setDataPaymentsDTO(creditCardDTO);

        return paymentDTO;

    }

    private Payment getPayment() {

        CreditCard creditCard = new CreditCard();

        creditCard.setCreditCardId("10");
        creditCard.setCardHolderName("JOAO DA SILVA");
        creditCard.setNumber("1234567890123456");
        creditCard.setCvvCode("123");
        creditCard.setExpirationDate(LocalDate.parse("2030-01-01"));

        Payment payment = new Payment();
        payment.setOrderId("1");
        payment.setPaymentId("10");
        payment.setPaymentType("CREDIT CARD");
        payment.setStatus("PAID");
        payment.setPaymentValue(350D);

        payment.setDataPayments(creditCard);

        return payment;

    }



}
