package lk.ijse.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentDTO implements Serializable {
    private String paymentID;

    private LocalDate paymentDate;
    private Double amount;

    public PaymentDTO() {

    }

    public PaymentDTO(String paymentID, LocalDate paymentDate, Double amount) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentID='" + paymentID + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }
}
