package lk.ijse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Payments {
    @Id
    private String paymentID;

    private LocalDate paymentDate;
    private Double amount;
    private Double balance;
    private Double paidAmount;

    @ManyToOne
    @JoinColumn(name = "stID")
    private Students students;

    @OneToOne(mappedBy = "payments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Programs programs;

    public Payments(String paymentID, LocalDate paymentDate, Double amount, Double balance, Double paidAmount) {
    }

    public Payments(String paymentID, LocalDate paymentDate, Double amount, Double balance, Double paidAmount, Students students, Programs programs) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.balance = balance;
        this.paidAmount = paidAmount;
        this.students = students;
        this.programs = programs;
    }

    public Payments() {

    }


    @Override
    public String toString() {
        return "Payments{" +
                "paymentID='" + paymentID + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", balance=" + balance +
                ", paidAmount=" + paidAmount +
                ", students=" + students +
                ", programs=" + programs +
                '}';
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

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }
}

