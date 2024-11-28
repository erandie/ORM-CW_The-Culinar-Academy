package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    private String userID;
    private String name;
    private String email;
    private String userName;
    private String password;
    private String position;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Students> studentsList;

    public User() {

    }

    public User(String userID, String name, String email, String userName, String password, String position) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.position = position;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Students> getStudents() {
        return studentsList;
    }

    public void setStudents(List<Students> students) {
        this.studentsList = students;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
