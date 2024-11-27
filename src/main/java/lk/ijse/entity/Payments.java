package lk.ijse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "Payments")
public class Payments {
    @Id
    private String paymentID;
    private String studentId;
    private String programId;
    private LocalDate paymentDate;
    private Double amount;
    private Double balance;
    private Double paidAmount;

    @ManyToOne
    @JoinColumn(name = "stID")
    private Students students;

    @OneToOne(mappedBy = "payments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Programs programs;

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

    public Payments(String paymentID, String studentId, String programId, LocalDate paymentDate, Double amount, Double balance, Double paidAmount) {
        this.paymentID = paymentID;
        this.studentId = studentId;
        this.programId = programId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.balance = balance;
        this.paidAmount = paidAmount;
    }

    public Payments() {
    }

    public Payments(String paymentID, String studentId, String programId, LocalDate paymentDate, Double amount, Double balance, Double paidAmount, Students students, Programs programs) {
        this.paymentID = paymentID;
        this.studentId = studentId;
        this.programId = programId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.balance = balance;
        this.paidAmount = paidAmount;
        this.students = students;
        this.programs = programs;
    }
}

