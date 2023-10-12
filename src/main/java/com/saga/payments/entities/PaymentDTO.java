package com.saga.payments.entities;

public class PaymentDTO {


    private Long orderId;
    private Long paymentId;
    private String paymentType;
    private Double paymentValue;
    private String status;
    private CreditCardDTO dataPaymentsDTO;


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
