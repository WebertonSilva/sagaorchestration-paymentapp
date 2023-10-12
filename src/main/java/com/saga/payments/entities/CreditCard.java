package com.saga.payments.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "creditcard")
public class CreditCard implements Serializable {

    @Null
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long creditCardId;

    @NotBlank
    @Column(nullable = false)
    private String cardHolderName;

    @NotBlank
    @Column(nullable = false)
    private String number;

    @NotBlank
    @Column(nullable = false)
    private String cvvCode;

    @NotBlank
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;


    @NotBlank
    @OneToOne(mappedBy = "dataPayments")
    private Payment payment;


    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(creditCardId, that.creditCardId) && Objects.equals(cardHolderName, that.cardHolderName) && Objects.equals(number, that.number) && Objects.equals(cvvCode, that.cvvCode) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardId, cardHolderName, number, cvvCode, expirationDate);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardId=" + creditCardId +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", number='" + number + '\'' +
                ", cvvCode='" + cvvCode + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
