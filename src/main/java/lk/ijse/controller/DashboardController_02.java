package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class DashboardController_02 {

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
            child_root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/programs-02.fxml")));
        } catch (IOException e) {}
    }

    @FXML
    void handlePaymentButtonClick(ActionEvent event) {
        child_root.getChildren().clear();
        try {
            child_root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Payment-02.fxml")));
        } catch (IOException e) {}
    }

}
