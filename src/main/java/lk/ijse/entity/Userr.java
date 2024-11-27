//package lk.ijse.entity;
//
//import jakarta.persistence.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//public class Userr {
//    @Id
//    private String userID;
//    private String name;
//    private String email;
//    private String userName;
//    private String password;
//    private String position;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Studentss> studentsses = new HashSet<>();
//
//    @Override
//    public String toString() {
//        return "Userr{" +
//                "userID='" + userID + '\'' +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                ", position='" + position + '\'' +
//                ", studentsses=" + studentsses +
//                '}';
//    }
//
//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    public Set<Studentss> getStudentsses() {
//        return studentsses;
//    }
//
//    public void setStudentsses(Set<Studentss> studentsses) {
//        this.studentsses = studentsses;
//    }
//
//    public Userr() {
//    }
//
//    public Userr(String userID, String name, String email, String userName, String password, String position, Set<Studentss> studentsses) {
//        this.userID = userID;
//        this.name = name;
//        this.email = email;
//        this.userName = userName;
//        this.password = password;
//        this.position = position;
//        this.studentsses = studentsses;
//    }
//}
