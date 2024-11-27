

package lk.ijse.dto;

import java.io.Serializable;
import java.util.Date;

public class StudentsDTO_02 implements Serializable {
    private String stID;
    private String stFullName;
    private String stAddress;
    private String stContact;
    private Date registrationDate;
    private String position;

    public StudentsDTO_02(String stID, String stFullName, String stAddress, String stContact, Date registrationDate, String position) {
        this.stID = stID;
        this.stFullName = stFullName;
        this.stAddress = stAddress;
        this.stContact = stContact;
        this.registrationDate = registrationDate;
        this.position = this.position;
    }

    public StudentsDTO_02(StudentsDTO_02 newStudent) {

    }

    public StudentsDTO_02() {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "StudentsDTO{" +
                "stID='" + stID + '\'' +
                ", stFullName='" + stFullName + '\'' +
                ", stAddress='" + stAddress + '\'' +
                ", stContact='" + stContact + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
