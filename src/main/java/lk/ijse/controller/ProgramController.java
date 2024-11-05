package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.ProgramBO;
import lk.ijse.dto.ProgramsDTO;

import java.util.List;

public class ProgramController {

    @FXML
    private Button btn_delete, btn_getAll, btn_save, btn_search, btn_update;

    @FXML
    private TableView<ProgramsDTO> tbl_prgrms;

    @FXML
    private TextField txt_drtion, txt_fee, txt_id, txt_name;

    @FXML
    private TableColumn<ProgramsDTO, String> clm_drtion;

    @FXML
    private TableColumn<ProgramsDTO, Double> clm_fee;

    @FXML
    private TableColumn<ProgramsDTO, String> clm_id;

    @FXML
    private TableColumn<ProgramsDTO, String> clm_name;


    private final ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);

    @FXML
    public void initialize() {
        // Set the columns with data properties
        clm_id.setCellValueFactory(new PropertyValueFactory<>("programID"));
        clm_name.setCellValueFactory(new PropertyValueFactory<>("prName"));
        clm_drtion.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clm_fee.setCellValueFactory(new PropertyValueFactory<>("prFee"));

        loadAllProgramsDetails();

        tbl_prgrms.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void populateFields(ProgramsDTO programs) {
        txt_id.setText(programs.getProgramID());
        txt_name.setText(programs.getPrName());
        txt_drtion.setText(programs.getDuration());
        txt_fee.setText(String.valueOf(programs.getPrFee()));
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

    public void save() {
        try {
            ProgramsDTO program = new ProgramsDTO(
                    txt_id.getText(),
                    txt_name.getText(),
                    txt_drtion.getText(),
                    Double.parseDouble(txt_fee.getText())
            );

            programBO.addProgram(program);
            new Alert(Alert.AlertType.CONFIRMATION, "Program Added Successfully!").show();
            loadAllProgramsDetails();
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Adding Program!").show();
        }
    }

    private void clearFields() {
        txt_id.clear();
        txt_name.clear();
        txt_drtion.clear();
        txt_fee.clear();
    }

    public void update() {
        try {
            ProgramsDTO program = new ProgramsDTO(
                    txt_id.getText(),
                    txt_name.getText(),
                    txt_drtion.getText(),
                    Double.parseDouble(txt_fee.getText())
            );
            programBO.UpdateProgram(program);
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated Successfully!").show();
            loadAllProgramsDetails();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Updating Program!").show();
        }
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
            ProgramsDTO programs = programBO.searchProgram(txt_id.getText());
            if (programs != null) {
                populateFields(programs);
            } else {
                new Alert(Alert.AlertType.WARNING, "Program Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Searching Program!").show();
        }
    }
}
