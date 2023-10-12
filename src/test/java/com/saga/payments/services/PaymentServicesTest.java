package com.saga.payments.services;


import com.saga.payments.entities.CreditCard;
import com.saga.payments.entities.CreditCardDTO;
import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.repositories.PaymentRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

//@WebMvcTest(AppController.class)
//@AutoConfigureMockMvc()
@SpringBootTest(classes = PaymentServicesTest.class)
public class PaymentServicesTest {

    @InjectMocks
    PaymentServices paymentService;

    @Mock
    PaymentRepository mockPaymentRepository;


    @Before
    public void beforeEach() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPayOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.save(payment)).thenReturn(payment);

        Long idPayment = paymentService.pay(payment);
        assertEquals(idPayment, 10L);
    }

    @Test
    public void testPayNOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.save(payment)).thenReturn(payment);

        Long idPayment = paymentService.pay(payment);
        assertNotEquals(idPayment, 1L);
    }

    @Test
    public void testGetPaymentByIdOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.findById(10L)).thenReturn(Optional.of(payment));

        PaymentDTO paymentDTO = paymentService.getPaymentById(10l);
        assertEquals(paymentDTO.getPaymentId(), 10L);
    }

    @Test
    public void testGetPaymentByIdNOk(){

        Payment payment = getPayment();
        when(mockPaymentRepository.findById(10L)).thenReturn(Optional.of(payment));

        PaymentDTO paymentDTO = paymentService.getPaymentById(10l);
        assertNotEquals(paymentDTO.getPaymentId(), 1L);
    }

    @Test
    public void testcancelPaymentByIdOk(){

        doNothing().when(mockPaymentRepository).cancelPayment(10L, "canceled");
        paymentService.cancelPaymentById(10L);

    }


    private PaymentDTO getPaymentDTO() {

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setCardHolderName("JOAO DA SILVA");
        creditCardDTO.setNumber("1234567890123456");
        creditCardDTO.setCvvCode("123");
        creditCardDTO.setExpirationDate(LocalDate.parse("2030-01-01"));

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(1L);
        paymentDTO.setPaymentType("CREDIT CARD");
        paymentDTO.setStatus("PAID");
        paymentDTO.setPaymentValue(350D);

        paymentDTO.setDataPaymentsDTO(creditCardDTO);

        return paymentDTO;

    }

    private Payment getPayment() {

        CreditCard creditCard = new CreditCard();

        creditCard.setCreditCardId(10L);
        creditCard.setCardHolderName("JOAO DA SILVA");
        creditCard.setNumber("1234567890123456");
        creditCard.setCvvCode("123");
        creditCard.setExpirationDate(LocalDate.parse("2030-01-01"));

        Payment payment = new Payment();
        payment.setOrderId(1L);
        payment.setPaymentId(10L);
        payment.setPaymentType("CREDIT CARD");
        payment.setStatus("PAID");
        payment.setPaymentValue(350D);

        payment.setDataPayments(creditCard);

        return payment;

    }



}
