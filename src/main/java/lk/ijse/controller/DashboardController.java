package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane mainPane; // AnchorPane where other FXML files will be loaded

    @FXML
    private Button btn_student; // Replace with actual button ID from FXML
    @FXML
    private Button btn_program; // Replace with actual button ID from FXML
    @FXML
    private Button btn_payment; // Replace with actual button ID from FXML
    @FXML
    private Button btn_user;

    @FXML
    private AnchorPane child_root;

//    @FXML
//    private void initialize() {
        // You can initialize any necessary data or set up event handlers here
//        btn_student.setOnAction(event -> loadFXML("/view/Students.fxml")); // Change to your FXML file
//        btn_program.setOnAction(event -> loadFXML("/view/Programs.fxml")); // Change to your FXML file
//        btn_payment.setOnAction(event -> loadFXML("otherFXML3.fxml")); // Change to your FXML file
//        btn_user.setOnAction(event -> loadFXML("otherFXML3.fxml")); // Change to your FXML file
//    }


    private void loadFXML(String s) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Students.fxml"));
            Parent root = loader.load();
            mainPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace(); // Log the error or handle it appropriately
        }
    }

    @FXML
    void handleStudentButtonClick(ActionEvent event) {
        child_root.getChildren().clear();
        try {
            child_root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Students.fxml")));
        } catch (IOException e) {}
    }

    @FXML
    void handleProgramButtonClick(ActionEvent event) {
        child_root.getChildren().clear();
        try {
            child_root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Programs.fxml")));
        } catch (IOException e) {}
    }

    @FXML
    void handlePaymentButtonClick(ActionEvent event) {
        child_root.getChildren().clear();
        try {
            child_root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Payment.fxml")));
        } catch (IOException e) {}
    }

    @FXML
    void handleUserButtonClick(ActionEvent event) {
        child_root.getChildren().clear();
        try {
            child_root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/User.fxml")));
        } catch (IOException e) {}
    }
}
