package com.saga.payments.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "payment")
public class Payment implements Serializable {


    @NotBlank
    @Column(nullable = false)
    private Long orderId;

    @Null
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @NotBlank
    @Column(nullable = false)
    private String paymentType;

    @NotBlank
    @Column(nullable = false)
    private Double paymentValue;

    @Null
    @Column(nullable = true)
    private String status;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CreditCard_creditCardId", referencedColumnName = "creditCardId")
    private CreditCard dataPayments;

    public Payment(){
    }


    public Payment(String paymentType, Double paymentValue) {
        this.paymentType = paymentType;
        this.paymentValue = paymentValue;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Double paymentValue) {
        this.paymentValue = paymentValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CreditCard getDataPayments() {
        return dataPayments;
    }

    public void setDataPayments(CreditCard dataPayments) {
        this.dataPayments = dataPayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentId == payment.paymentId && Objects.equals(paymentType, payment.paymentType) && Objects.equals(paymentValue, payment.paymentValue) && Objects.equals(dataPayments, payment.dataPayments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, paymentType, paymentValue, dataPayments);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentType='" + paymentType + '\'' +
                ", paymentValue=" + paymentValue +
                ", dataPayments=" + dataPayments +
                '}';
    }
}
