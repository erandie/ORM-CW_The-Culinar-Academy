package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.ProgramBO;
import lk.ijse.dto.ProgramsDTO;
import lk.ijse.dto.StudentsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProgramController {

    @FXML
    private Button btn_delete, btn_getAll, btn_save, btn_search, btn_update, btnAddNew;

    @FXML
    private TableView<ProgramsDTO> tbl_prgrms;

    @FXML
    private TextField txt_drtion, txt_fee, txt_id, txt_name, txtSearch;

    @FXML
    private TableColumn<ProgramsDTO, String> col_pr_duration;

    @FXML
    private TableColumn<ProgramsDTO, Double> col_pr_fee;

    @FXML
    private TableColumn<ProgramsDTO, String> col_pr_id;

    @FXML
    private TableColumn<ProgramsDTO, String> col_pr_name;


    private final ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);

    @FXML
    public void initialize() {
        // Set the columns with data properties
        col_pr_id.setCellValueFactory(new PropertyValueFactory<>("programID"));
        col_pr_name.setCellValueFactory(new PropertyValueFactory<>("prName"));
        col_pr_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_pr_fee.setCellValueFactory(new PropertyValueFactory<>("prFee"));

        txt_name.setOnAction(actionEvent -> txt_drtion.requestFocus());
        txt_drtion.setOnAction(actionEvent -> txt_fee.requestFocus());

        loadAllProgramsDetails();

        tbl_prgrms.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });

        initUI();

    }

    private void initUI() {
        clearFields();
        txt_id.setDisable(true);
        txt_name.setDisable(true);
        txt_drtion.setDisable(true);
        txt_fee.setDisable(true);
        btn_save.setDisable(false);
        btn_delete.setDisable(false);
    }

    private void populateFields(ProgramsDTO programs) {
        txt_id.setText(programs.getProgramID());
        txt_name.setText(programs.getPrName());
        txt_drtion.setText(programs.getDuration());
        txt_fee.setText(String.valueOf(programs.getPrFee()));


        txt_id.setDisable(false);
        txt_name.setDisable(false);
        txt_drtion.setDisable(false);
        txt_fee.setDisable(false);

        btn_save.setText("Update");
        btn_save.setDisable(false);
    }

    private void loadAllProgramsDetails() {
        try {
            List<ProgramsDTO> allPrograms = programBO.getAllPrograms();
            ObservableList<ProgramsDTO> items = FXCollections.observableArrayList(allPrograms);
            tbl_prgrms.setItems(items);
            tbl_prgrms.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Loading Programs!").show();
        }
    }

    public void save(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String pId = txt_id.getText();
        String name = txt_name.getText();
        String duration = txt_drtion.getText();
        String feeText = txt_fee.getText();

        double fee = 0.0;
        try {
            fee = Double.parseDouble(feeText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid fee value. Please enter a valid number.").show();
            return;
        }

        if (btn_save.getText().equalsIgnoreCase("Save")) {
            try {
                if (exitStudent(pId)) {
                    new Alert(Alert.AlertType.ERROR, pId + " already exists!").show();
                    return;
                }

                programBO.addProgram(new ProgramsDTO(pId, name, duration, fee));

                tbl_prgrms.getItems().add(new ProgramsDTO(pId, name, duration, fee));

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the program! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                if (!exitStudent(pId)) {
                    new Alert(Alert.AlertType.ERROR, "Can't find the ID " + pId + "! Enter another one!").show();
                    return;
                }

                programBO.UpdateProgram(new ProgramsDTO(pId, name, duration, fee));

                ProgramsDTO selectedProgram = tbl_prgrms.getSelectionModel().getSelectedItem();
                selectedProgram.setPrName(name);
                selectedProgram.setDuration(duration);
                selectedProgram.setPrFee(fee);
                tbl_prgrms.refresh();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the program! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        btnAddNew.fire();
    }

    private boolean exitStudent(String pId) throws Exception {
        return programBO.existProgram(pId);
    }


    private void clearFields() {
        txt_id.clear();
        txt_name.clear();
        txt_drtion.clear();
        txt_fee.clear();
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txt_id.setDisable(false);
        txt_name.setDisable(false);
        txt_drtion.setDisable(false);
        txt_fee.setDisable(false);

        txt_id.clear();
        txt_id.setText(generateNewId());
        txt_name.clear();
        txt_drtion.clear();
        txt_fee.clear();

        txt_name.requestFocus();
        btn_save.setDisable(false);
        btn_save.setText("save");
        tbl_prgrms.getSelectionModel().clearSelection();
    }

    private String generateNewId() {
        try {
            return programBO.generateNew_ProgramID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "failed to generate a new id!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tbl_prgrms.getItems().isEmpty()) {
            return "P001";
        } else {
            String sId = getLastProgrmID();
            int newProgrmID = Integer.parseInt(sId.replace("S", "")) + 1;
            return String.format("P00-%03d", newProgrmID);
        }
    }

    private String getLastProgrmID() {
        List<ProgramsDTO> tempPrgrmList = new ArrayList<>(tbl_prgrms.getItems());

        if (tempPrgrmList.isEmpty()) {
            return null;
        }

        tempPrgrmList.sort(Comparator.comparing(ProgramsDTO::getProgramID));

        return tempPrgrmList.get(tempPrgrmList.size() - 1).getProgramID();
    }

    public void delete() {
        try {
            if (programBO.deleteProgram(txt_id.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Program Deleted!").show();
                loadAllProgramsDetails();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Program Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Deleting Program!").show();
        }
    }

    public void search() {
        try {
            String programId = txtSearch.getText();
            ProgramsDTO programs = programBO.searchProgram(programId);
            if (programs != null) {
                populateFields(programs);
            } else {
                new Alert(Alert.AlertType.WARNING, "Program Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Searching Program!").show();
        }
        txtSearch.clear();
    }


    public void btnAdd_OnAction(ActionEvent actionEvent) {

    }

    public void btnNext_OnAction(ActionEvent actionEvent) {

    }

    public void txtCustomerNAmeOnKeyReleased(KeyEvent keyEvent) {

    }

    public void txtCustomerContactOnKeyReleased(KeyEvent keyEvent) {

    }
}
