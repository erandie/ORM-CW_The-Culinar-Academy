package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.DAO.custom.Implement.ProgramDAOImpl;
import lk.ijse.DAO.custom.Implement.StudentDAOImpl;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.StudentsDTO;
import lk.ijse.entity.Programs;
import lk.ijse.entity.Students;

import java.util.Date;
import java.util.List;

public class PaymentController {
    @FXML
    private ComboBox<String> cmb_progrm_id;

    @FXML
    private ComboBox<String> cmb_student_id;

    @FXML
    private TextField txt_amount;

    @FXML
    private Button btn_updateBalance;

    @FXML
    private TextField txt_full_payment;

    @FXML
    private TextField txt_paid_amount;

    @FXML
    private DatePicker txt_pay_date;

    @FXML
    private TextField txt_payment_id;

    @FXML
    private TextField txt_prgrm_name;

    @FXML
    private TextField txt_student_name;

    @FXML
    private TableView<PaymentDTO> tbl_payment;

    private ProgramDAOImpl programDAOImpl = new ProgramDAOImpl();
    private StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() throws Exception {
        List<Programs> programsList = programDAOImpl.getAll();
        for (Programs programs : programsList) {
            cmb_progrm_id.getItems().add(programs.getProgramID());
        }

        List<Students> studentsList = studentDAOImpl.getAll();
        for (Students students : studentsList) {
            cmb_student_id.getItems().add(students.getStID());
        }

        cmb_student_id.setOnAction(this::load_student);
        cmb_progrm_id.setOnAction(this::load_program);

        tbl_payment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        tbl_payment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tbl_payment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("programID"));
        tbl_payment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbl_payment.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        tbl_payment.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("balance"));
        tbl_payment.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        loadAllPaymentsDetails();

        tbl_payment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void populateFields(PaymentDTO payment) {
        txt_payment_id.setText(String.valueOf(payment.getPaymentID()));
        txt_pay_date.setValue(payment.getPaymentDate());
        txt_amount.setText(String.valueOf(payment.getAmount()));
        btn_updateBalance.setText(String.valueOf(payment.getBalance()));
        txt_paid_amount.setText(String.valueOf(payment.getPaidAmount()));
    }

    private void loadAllPaymentsDetails() {
        try {
            List<PaymentDTO> allPayments = paymentBO.getAllPayments();
            ObservableList<PaymentDTO> items = FXCollections.observableArrayList(allPayments);
            tbl_payment.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Loading Payments").show();
        }
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
                    txt_amount.setText(String.valueOf(programs.getPrFee()));
                } else {
                    txt_prgrm_name.clear();
                    txt_amount.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBalance(ActionEvent event) {
        try {
            String fullPayment = txt_amount.getText();
            String paidAmount = txt_paid_amount.getText();

            if (!fullPayment.isEmpty() && !paidAmount.isEmpty()) {
                double fullPaymentValue = Double.parseDouble(fullPayment);
                double paidAmountValue = Double.parseDouble(paidAmount);

                double balance = fullPaymentValue - paidAmountValue;
                btn_updateBalance.setText(String.valueOf(balance));
            }
        } catch (NumberFormatException e) {
            btn_updateBalance.setText("");
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) {
        try {
            PaymentDTO payment = new PaymentDTO(
                    txt_payment_id.getId(),
                    txt_pay_date.getValue(),
                    Double.parseDouble(txt_amount.getText()),
                    Double.parseDouble(btn_updateBalance.getText()),
                    Double.parseDouble(txt_paid_amount.getText())
            );

            paymentBO.addPayment(payment);
            new Alert(Alert.AlertType.CONFIRMATION, "Payment added successfully").show();
            loadAllPaymentsDetails();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error saving payment" + e).show();
        }
    }


    private void clearFields() {
        txt_payment_id.clear();
        txt_pay_date.setValue(null);
        txt_amount.clear();
        btn_updateBalance.setText("");
        txt_paid_amount.clear();
        txt_student_name.clear();
        txt_prgrm_name.clear();
    }

    public void update(ActionEvent actionEvent) {
        try {
            StudentsDTO student = new StudentsDTO(
                    txt_payment_id.getText(),
                    txt_student_name.getText(),
                    "", // Placeholder for other fields if needed
                    "", // Placeholder for contact if needed
                    new Date() // Ensure parsing of date as needed
            );
            studentBO.updateStudents(student);
            new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully!").show();
            loadAllPaymentsDetails();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Updating Student").show();
        }
    }

    public void delete(ActionEvent actionEvent) {
        try {
            if (paymentBO.deletePayment(txt_payment_id.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted Successfully!").show();
                loadAllPaymentsDetails();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Payment Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Deleting Payment").show();
        }
    }

    public void search(ActionEvent actionEvent) {
        try {
            PaymentDTO payment = paymentBO.searchPayment(txt_payment_id.getText());
            if (payment != null) {
                populateFields(payment);
            } else {
                new Alert(Alert.AlertType.WARNING, "Payment Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Searching Payment").show();
        }
    }

    public void getAll(ActionEvent actionEvent) {
        loadAllPaymentsDetails();
    }
}
