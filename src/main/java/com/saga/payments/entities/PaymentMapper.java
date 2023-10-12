package com.saga.payments.entities;

public class PaymentMapper {

    public static PaymentDTO mapToDTO(Payment payment){

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setPaymentType(payment.getPaymentType());
        paymentDTO.setPaymentValue(payment.getPaymentValue());
        paymentDTO.setStatus(payment.getStatus());

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCreditCardId(payment.getDataPayments().getCreditCardId());
        creditCardDTO.setCardHolderName(payment.getDataPayments().getCardHolderName());
        creditCardDTO.setNumber(payment.getDataPayments().getNumber());
        creditCardDTO.setCvvCode(payment.getDataPayments().getCvvCode());
        creditCardDTO.setExpirationDate(payment.getDataPayments().getExpirationDate());

        paymentDTO.setDataPaymentsDTO(creditCardDTO);

        return paymentDTO;
    }
}
