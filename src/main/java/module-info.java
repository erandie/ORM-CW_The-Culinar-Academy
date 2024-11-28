module ORM.Coursework.The.Culinary.Academy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens lk.ijse to javafx.fxml;
    exports lk.ijse;
    exports lk.ijse.controller;
    opens lk.ijse.entity;
    opens lk.ijse.dto;
    opens lk.ijse.controller to javafx.fxml;

}
