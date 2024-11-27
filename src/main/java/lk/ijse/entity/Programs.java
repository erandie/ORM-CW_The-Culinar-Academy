package lk.ijse.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Programs {
    @Id
    private String programID;
    private String prName;
    private String duration;
    private Double prFee;

    @ManyToMany
    private List<Students> students;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "programID")
    private Payments payments;

    public Programs() {
    }

    public Programs(String programID, String prName, String duration, Double prFee) {
        this.programID = programID;
        this.prName = prName;
        this.duration = duration;
        this.prFee = prFee;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrFee() {
        return prFee;
    }

    public void setPrFee(Double prFee) {
        this.prFee = prFee;
    }

    @Override
    public String toString() {
        return "Programs{" +
                "students=" + students +
                '}';
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public Programs(List<Students> students) {
        this.students = students;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }
}
