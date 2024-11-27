package lk.ijse.dto;

import jakarta.persistence.*;
import lk.ijse.entity.Programs;
import lk.ijse.entity.Students;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentDTO implements Serializable {
    private String paymentID;
    private String studentId;
    private String programId;
    private LocalDate paymentDate;
    private Double amount;
    private Double balance;
    private Double paidAmount;

    @Override
    public String toString() {
        return "Payments{" +
                "paymentID='" + paymentID + '\'' +
                ", studentId='" + studentId + '\'' +
                ", programId='" + programId + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", balance=" + balance +
                ", paidAmount=" + paidAmount +
                '}';
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public PaymentDTO(String paymentID, String studentId, String programId, LocalDate paymentDate, Double amount, Double balance, Double paidAmount) {
        this.paymentID = paymentID;
        this.studentId = studentId;
        this.programId = programId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.balance = balance;
        this.paidAmount = paidAmount;
    }

    public PaymentDTO() {
    }




}

