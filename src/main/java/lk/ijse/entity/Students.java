package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Students {

    @Id
    private String stID;
    private String stFullName;
    private String stAddress;
    private String stContact;
    private Date registrationDate;
    private String programID;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Student_programs",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )

    private Set<Programs> programs = new HashSet<>();

    public Students() {
    }

    public Students(String stID, String stFullName, String stAddress, String stContact, Date registrationDate, String programID, Set<Programs> programs) {
        this.stID = stID;
        this.stFullName = stFullName;
        this.stAddress = stAddress;
        this.stContact = stContact;
        this.registrationDate = registrationDate;
        this.programID = programID;
        this.programs = programs;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStFullName() {
        return stFullName;
    }

    public void setStFullName(String stFullName) {
        this.stFullName = stFullName;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getStContact() {
        return stContact;
    }

    public void setStContact(String stContact) {
        this.stContact = stContact;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public Set<Programs> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Programs> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "Students{" +
                "stID='" + stID + '\'' +
                ", stFullName='" + stFullName + '\'' +
                ", stAddress='" + stAddress + '\'' +
                ", stContact='" + stContact + '\'' +
                ", registrationDate=" + registrationDate +
                ", programID='" + programID + '\'' +
                ", programs=" + programs +
                '}';
    }
}
