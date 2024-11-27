package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Students {
    @Id
    private String stID;
    private String stFullName;
    private String stAddress;

    @Override
    public String toString() {
        return "Students{" +
                "programs=" + programs +
                '}';
    }

    public List<Programs> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Programs> programs) {
        this.programs = programs;
    }

    public Students(List<Programs> programs) {
        this.programs = programs;
    }

    private String stContact;
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToMany(mappedBy = "students")
    private List<Programs> programs;

    public Students() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Students(String stID, String stFullName, String stAddress, String stContact, Date registrationDate, String position) {
    }

    public Students(String stID, String stFullName, String stAddress, String stContact, Date registrationDate, User user) {
        this.stID = stID;
        this.stFullName = stFullName;
        this.stAddress = stAddress;
        this.stContact = stContact;
        this.registrationDate = registrationDate;
        this.user = user;
    }
}
