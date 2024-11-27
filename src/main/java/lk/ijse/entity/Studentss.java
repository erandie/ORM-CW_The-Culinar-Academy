//package lk.ijse.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//import java.util.Date;
//
//@Entity
//public class Studentss {
//    @Id
//    private String stID;
//    private String stFullName;
//    private String stAddress;
//    private String stContact;
//    private Date registrationDate;
//
//    @ManyToOne
//    @JoinColumn(name = "userID")
//    private User user;
//
//    @Override
//    public String toString() {
//        return "Studentss{" +
//                "stID='" + stID + '\'' +
//                ", stFullName='" + stFullName + '\'' +
//                ", stAddress='" + stAddress + '\'' +
//                ", stContact='" + stContact + '\'' +
//                ", registrationDate=" + registrationDate +
//                ", user=" + user +
//                '}';
//    }
//
//    public String getStID() {
//        return stID;
//    }
//
//    public void setStID(String stID) {
//        this.stID = stID;
//    }
//
//    public String getStFullName() {
//        return stFullName;
//    }
//
//    public void setStFullName(String stFullName) {
//        this.stFullName = stFullName;
//    }
//
//    public String getStAddress() {
//        return stAddress;
//    }
//
//    public void setStAddress(String stAddress) {
//        this.stAddress = stAddress;
//    }
//
//    public String getStContact() {
//        return stContact;
//    }
//
//    public void setStContact(String stContact) {
//        this.stContact = stContact;
//    }
//
//    public Date getRegistrationDate() {
//        return registrationDate;
//    }
//
//    public void setRegistrationDate(Date registrationDate) {
//        this.registrationDate = registrationDate;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Studentss() {
//    }
//
//    public Studentss(String stID, String stFullName, String stAddress, String stContact, Date registrationDate, User user) {
//        this.stID = stID;
//        this.stFullName = stFullName;
//        this.stAddress = stAddress;
//        this.stContact = stContact;
//        this.registrationDate = registrationDate;
//        this.user = user;
//    }
//}
