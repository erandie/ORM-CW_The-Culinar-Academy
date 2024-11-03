package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Payments {
    @Id
    private String paymentID;

    private LocalDate paymentDate;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "stID")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "programID")
    private Programs programs;

    public Payments() {
    }

    public Payments(String paymentID, LocalDate paymentDate, Double amount, Students students, Programs programs) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.students = students;
        this.programs = programs;
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

    @Override
    public String toString() {
        return "Payments{" +
                "paymentID='" + paymentID + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", students=" + students +
                ", programs=" + programs +
                '}';
    }
}
