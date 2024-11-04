package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Programs {

    @Id
    private String programID;
    private String prName;
    private String duration;
    private Double prFee;

    @ManyToMany(mappedBy = "programs",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Students> students = new HashSet<>();

    public Programs() {
    }

    public Programs(String programID, String prName, String duration, Double prFee, Set<Students> students) {
        this.programID = programID;
        this.prName = prName;
        this.duration = duration;
        this.prFee = prFee;
        this.students = students;
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

    public Set<Students> getStudents() {
        return students;
    }

    public void setStudents(Set<Students> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Programs{" +
                "programID='" + programID + '\'' +
                ", prName='" + prName + '\'' +
                ", duration='" + duration + '\'' +
                ", prFee=" + prFee +
                ", students=" + students +
                '}';
    }
}
