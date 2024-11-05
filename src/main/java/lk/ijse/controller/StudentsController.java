package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.dto.ProgramsDTO;
import lk.ijse.dto.StudentsDTO;

import java.util.Date;
import java.util.List;

public class StudentsController {

    @FXML private TextField txt_id, txt_name, txt_address, txt_contact, txt_date;
    @FXML private Button btn_delete, btn_getAll, btn_save, btn_search, btn_update;
    @FXML private TableView<StudentsDTO> tbl_stdnts;
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        tbl_stdnts.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stID"));
        tbl_stdnts.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("stFullName"));
        tbl_stdnts.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("stAddress"));
        tbl_stdnts.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("stContact"));
        tbl_stdnts.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        loadAllStudents();

        // Set up table row click listener
        tbl_stdnts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void populateFields(StudentsDTO student) {
        txt_id.setText(student.getStID());
        txt_name.setText(student.getStFullName());
        txt_address.setText(student.getStAddress());
        txt_contact.setText(student.getStContact());
        txt_date.setText(student.getRegistrationDate().toString()); // Format as needed
    }

    public void save() {
        try {
            StudentsDTO student = new StudentsDTO(
                    txt_id.getText(),
                    txt_name.getText(),
                    txt_address.getText(),
                    txt_contact.getText(),
                    new Date() // Parse date if required
            );
            studentBO.addStudents(student);
            new Alert(Alert.AlertType.CONFIRMATION, "Student Added Successfully!").show();
            loadAllStudents();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Adding Student").show();
        }
    }

    public void update() {
        try {
            StudentsDTO student = new StudentsDTO(
                    txt_id.getText(),
                    txt_name.getText(),
                    txt_address.getText(),
                    txt_contact.getText(),
                    new Date() // Parse date if required
            );
            studentBO.updateStudents(student);
            new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully!").show();
            loadAllStudents();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Updating Student").show();
        }
    }

    public void delete() {
        try {
            if (studentBO.deleteStudents(txt_id.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted Successfully!").show();
                loadAllStudents();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Student Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Deleting Student").show();
        }
    }

    public void search() {
        try {
            StudentsDTO student = studentBO.searchStudent(txt_id.getText());
            if (student != null) {
                populateFields(student);
            } else {
                new Alert(Alert.AlertType.WARNING, "Student Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Searching Student").show();
        }
    }

    public void loadAllStudents() {
        try {
            List<StudentsDTO> allStudents = studentBO.getAllStudents();
            ObservableList<StudentsDTO> items = FXCollections.observableArrayList(allStudents);
            tbl_stdnts.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Loading Students").show();
        }
    }

    private void clearFields() {
        txt_id.clear();
        txt_name.clear();
        txt_address.clear();
        txt_contact.clear();
        txt_date.clear();
    }
}


