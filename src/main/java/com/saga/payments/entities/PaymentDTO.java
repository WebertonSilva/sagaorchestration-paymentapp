package com.saga.payments.entities;

public class PaymentDTO {


    private String orderId;
    private String paymentId;
    private String paymentType;
    private Double paymentValue;
    private String status;
    private CreditCardDTO dataPaymentsDTO;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
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

    public CreditCardDTO getDataPaymentsDTO() {
        return dataPaymentsDTO;
    }

    public void setDataPaymentsDTO(CreditCardDTO dataPaymentsDTO) {
        this.dataPaymentsDTO = dataPaymentsDTO;
    }


    @Override
    public String toString() {
        return "PaymentDTO{" +
                "orderId=" + orderId +
                ", paymentId=" + paymentId +
                ", paymentType='" + paymentType + '\'' +
                ", paymentValue=" + paymentValue +
                ", status='" + status + '\'' +
                ", dataPaymentsDTO=" + dataPaymentsDTO +
                '}';
    }
}
