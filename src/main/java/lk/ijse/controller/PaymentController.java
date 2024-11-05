package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.DAO.custom.Implement.ProgramDAOImpl;
import lk.ijse.DAO.custom.Implement.StudentDAOImpl;
import lk.ijse.entity.Programs;
import lk.ijse.entity.Students;

import java.util.List;

public class PaymentController {
    @FXML
    private ComboBox<String> cmb_progrm_id;

    @FXML
    private ComboBox<String> cmb_student_id;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_balance;

    @FXML
    private TextField txt_full_payment;

    @FXML
    private TextField txt_paid_amount;

    @FXML
    private TextField txt_pay_date;

    @FXML
    private TextField txt_payment_id;

    @FXML
    private TextField txt_prgrm_name;

    @FXML
    private TextField txt_student_name;

    private ProgramDAOImpl programDAOImpl = new ProgramDAOImpl();
    private StudentDAOImpl studentDAOImpl = new StudentDAOImpl();

    public void initialize() throws Exception {
        List<Programs> programsList =programDAOImpl.getAll();

        for (Programs programs : programsList) {
            cmb_progrm_id.getItems().add(programs.getProgramID());
        }

        List<Students> studentsList = studentDAOImpl.getAll();

        for (Students students : studentsList) {
            cmb_student_id.getItems().add(students.getStID());
        }

        cmb_student_id.setOnAction(this::load_student);
    }

    public void load_student(ActionEvent actionEvent) {
        String selectedStudentId = cmb_student_id.getSelectionModel().getSelectedItem();

        if (selectedStudentId != null) {
            try {
                Students students = studentDAOImpl.search(selectedStudentId);

                if (students != null) {
                    txt_student_name.setText(students.getStFullName());
                } else {
                    txt_student_name.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void load_program(ActionEvent actionEvent) {
        String selectedProgrammeId = cmb_progrm_id.getSelectionModel().getSelectedItem();

        if (selectedProgrammeId != null) {
            try {
                Programs programs = programDAOImpl.search(selectedProgrammeId);

                if (programs != null) {
                    txt_prgrm_name.setText(programs.getPrName());
                } else {
                    txt_prgrm_name.clear();
                }

                if (programs != null) {
                    txt_amount.setText(String.valueOf(programs.getPrFee()));
                } else {
                    txt_amount.clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBalance(){
        try {
            String
        }
    }

}
