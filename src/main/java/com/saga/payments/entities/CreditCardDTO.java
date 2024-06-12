package com.saga.payments.entities;

import java.time.LocalDate;

public class CreditCardDTO {

    private String creditCardId;

    private String cardHolderName;

    private String number;

    private String cvvCode;

    private LocalDate expirationDate;


    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "creditCardId=" + creditCardId +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", number='" + number + '\'' +
                ", cvvCode='" + cvvCode + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
