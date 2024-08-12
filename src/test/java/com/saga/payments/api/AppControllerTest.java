package com.saga.payments.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.payments.entities.CreditCard;
import com.saga.payments.entities.CreditCardDTO;
import com.saga.payments.entities.Payment;
import com.saga.payments.entities.PaymentDTO;
import com.saga.payments.services.PaymentServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc()
public class AppControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AppController appController;

    @Mock
    private PaymentServices mockPaymentServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    public void testPayOk() throws Exception {

        Payment payment = getPayment();
        when(mockPaymentServices.pay(payment)).thenReturn("1");
        final ResultActions result = mockMvc.perform(post("/payments/pay").content(mapToJson(payment)).contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void testPayNOk() throws Exception {

        Payment payment = new Payment();
        when(mockPaymentServices.pay(payment)).thenReturn("1");
        final ResultActions result = mockMvc.perform(post("/payments/pay").content((byte[]) null).contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testCancelPaymentOK() throws Exception {

        doNothing().when(mockPaymentServices).cancelPaymentById("10");
        final ResultActions result = mockMvc.perform(post("/payments/cancelPayment/10"));

        result.andExpect(status().isOk());
    }

    @Test
    public void testCancelPaymentNOK() throws Exception {

        doNothing().when(mockPaymentServices).cancelPaymentById("10");
        final ResultActions result = mockMvc.perform(post("/payments/cancelPayment/null"));

        result.andExpect(status().isOk());
    }

    @Test
    public void testGetByIdOK() throws Exception {

        PaymentDTO paymentDTO = getPaymentDTO();
        when(mockPaymentServices.getPaymentById("10")).thenReturn(paymentDTO);

        final ResultActions result = mockMvc.perform(get("/payments/getPayment/10"));

        result.andExpect(status().isFound());
    }


    @Test
    public void testGetByIdNOK() throws Exception {

        PaymentDTO paymentDTO = getPaymentDTO();
        when(mockPaymentServices.getPaymentById("10")).thenReturn(paymentDTO);

        final ResultActions result = mockMvc.perform(get("/payments/getPayment/null"));

        result.andExpect(status().isNotFound());
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


    private String mapToJson(Object objects) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(objectMapper);
    }

}
