package lk.ijse.controller;

//import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.DAO.custom.Implement.ProgramDAOImpl;
import lk.ijse.DAO.custom.ProgramsDAO;
import lk.ijse.dto.StudentsDTO;
import lk.ijse.entity.Programs;

import java.sql.SQLException;
import java.util.*;

public class StudentsController {

    @FXML private TextField txt_id, txt_name, txt_address, txt_contact;
    @FXML private Button btn_delete, btn_getAll, btn_save, btn_search, btn_update, btnAddNew;
    @FXML private TableView<StudentsDTO> tbl_stdnts;
    @FXML
    private ComboBox<String> cmb_position;
    @FXML
    private PasswordField txt_password;
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private final ProgramDAOImpl programDAO = new ProgramDAOImpl();
    @FXML
    private DatePicker txt_date;

    @FXML
    private ComboBox<String> cmb_prgrm;


    private ObservableList<String> positionTypes = FXCollections.observableArrayList("Admin", "Coordinator");

    public void initialize() throws Exception {
        tbl_stdnts.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stID"));
        tbl_stdnts.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("stFullName"));
        tbl_stdnts.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("stAddress"));
        tbl_stdnts.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("stContact"));
        tbl_stdnts.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        tbl_stdnts.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("position"));


        txt_name.setOnAction(actionEvent -> txt_address.requestFocus());
        txt_address.setOnAction(actionEvent -> txt_contact.requestFocus());
        txt_contact.setOnAction(actionEvent -> txt_date.requestFocus());

        loadPrgrmComboBox();
        loadAllStudents();

        cmb_position.setItems(positionTypes);

//        addRegex(txt_id, "^C\\d{2}-\\d{3}$", "ID must follow the pattern SXX-XXX");
//        addRegex(txt_name, "^[A-Za-z]+(?:[\\s-][A-Za-z]+)*$", "Name should start with a capital letter and contain only letters");
//        addRegex(txt_address, "^[A-Za-z]+(?:[\\s-][A-Za-z]+)*$", "Address should start with a capital letter and contain only letters");
//        addRegex(txt_contact, "^(\\+?\\d{10,12})$", "Contact number should be 10-12 digits, optionally starting with +");


        // Set up table row click listener
        tbl_stdnts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });

        initUI();


    }

    private void loadPrgrmComboBox() throws Exception {
        List<Programs> programsList = programDAO.getAll();
        for (Programs programs : programsList) {
            cmb_prgrm.getItems().add(programs.getProgramID());
        }

    }

    private void populateFields(StudentsDTO student) {
        txt_id.setText(student.getStID());
        txt_name.setText(student.getStFullName());
        txt_address.setText(student.getStAddress());
        txt_contact.setText(student.getStContact());
        txt_date.getValue();
        cmb_position.setValue(student.getPosition());

        txt_id.setDisable(false);
        txt_name.setDisable(false);
        txt_address.setDisable(false);
        txt_contact.setDisable(false);
        txt_date.setDisable(false);

        btn_save.setText("Update");
        btn_save.setDisable(false);
    }

    private String generateNewId() {
        try {
            return studentBO.generateNew_StudentID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "failed to generate a new id!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tbl_stdnts.getItems().isEmpty()) {
            return "S001";
        } else {
            String sId = getLastStudentID();
            int newStudentID = Integer.parseInt(sId.replace("S", "")) + 1;
            return String.format("S00-%03d", newStudentID);
        }
    }



    private String getLastStudentID() {
        List<StudentsDTO> tempStudentList = new ArrayList<>(tbl_stdnts.getItems());

        if (tempStudentList.isEmpty()) {
            return null;
        }

        tempStudentList.sort(Comparator.comparing(StudentsDTO::getStID));

        return tempStudentList.get(tempStudentList.size() - 1).getStID();
    }


    public void save(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String sId = txt_id.getText();
        String name = txt_name.getText();
        String address = txt_address.getText();
        String contact = txt_contact.getText();
        String position = cmb_position.getValue();
        new Date();

        if (btn_save.getText().equalsIgnoreCase("Save")) {
            try {
                if (exitStudent(sId)) {
                    new Alert(Alert.AlertType.ERROR, sId + " already exists!").show();
                    return;
                }

                StudentsDTO newStudent = new StudentsDTO(sId, name, address, contact, new Date(), position);
                studentBO.addStudents(new StudentsDTO(sId, name, address, contact, new Date(), position));
                tbl_stdnts.getItems().add(newStudent);

                new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully!").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error saving student: " + e.getMessage()).show();
            }

        } else {
            try {
                if (!exitStudent(sId)) {
                    new Alert(Alert.AlertType.ERROR, "Can't find the ID " + sId + "! Enter another one!").show();
                    return;
                }

                StudentsDTO updatedStudent = new StudentsDTO(sId, name, address, contact, new Date(), position);
                studentBO.updateStudents(new StudentsDTO(sId, name, address, contact, new Date(), position));
                StudentsDTO selectedStudent = tbl_stdnts.getSelectionModel().getSelectedItem();
                selectedStudent.setStFullName(name);
                selectedStudent.setStAddress(address);
                selectedStudent.setStContact(contact);
                selectedStudent.setRegistrationDate(new Date());
                selectedStudent.setPosition(position);
                tbl_stdnts.refresh();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the student! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        btnAddNew.fire();
    }

    private boolean exitStudent(String sId) throws Exception {
        return studentBO.existStudent(sId);
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
            new Alert(Alert.AlertType.ERROR, "Error Loading Students" + e).show();
        }
    }

    private void initUI() {
        clearFields();
        txt_id.setDisable(true);
        txt_name.setDisable(true);
        txt_address.setDisable(true);
        txt_contact.setDisable(true);
        txt_date.setDisable(true);
        cmb_position.setDisable(true);
        btn_save.setDisable(false);
        btn_delete.setDisable(true);
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txt_id.setDisable(false);
        txt_name.setDisable(false);
        txt_address.setDisable(false);
        txt_contact.setDisable(false);
        txt_date.setDisable(false);
        cmb_position.setDisable(false);

        txt_id.clear();
        txt_id.setText(generateNewId());
        txt_name.clear();
        txt_address.clear();
        txt_contact.clear();
        txt_date.setValue(null);
        cmb_position.getSelectionModel().clearSelection();

        txt_name.requestFocus();
        btn_save.setDisable(false);
        btn_save.setText("Save");
        tbl_stdnts.getSelectionModel().clearSelection();
    }




//    private void addRegex(JFXTextField textField, String pattern, String message) {
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches(pattern)) {
//                if (!textField.getStyleClass().contains("error")) {
//                    textField.getStyleClass().add("error");
//                }
//            } else {
//                textField.getStyleClass().remove("error");
//            }
//        });
//    }


    private void clearFields() {
        txt_id.clear();
        txt_name.clear();
        txt_address.clear();
        txt_contact.clear();
        txt_date.setValue(null);
        cmb_position.getSelectionModel().clearSelection();
    }

    public void txtCustomerNAmeOnKeyReleased(KeyEvent keyEvent) {

    }

    public void txtCustomerContactOnKeyReleased(KeyEvent keyEvent) {

    }

}
