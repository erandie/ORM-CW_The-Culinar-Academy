package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.DAO.custom.Implement.ProgramDAOImpl;
import lk.ijse.DAO.custom.Implement.StudentDAOImpl;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.ProgramsDTO;
import lk.ijse.entity.Programs;
import lk.ijse.entity.Students;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
    private TextField txt_student_name, txtSearch;

    @FXML
    private TableView<PaymentDTO> tbl_payment;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_save;

    @FXML
    private Button btnAddNew;

    private final ProgramDAOImpl programDAOImpl = new ProgramDAOImpl();
    private final StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() throws Exception {
        loadComboBoxes();
        setupTableColumns();
        loadAllPaymentsDetails();
        generatePaymentId();


        cmb_student_id.setOnAction(this::load_student);
        cmb_progrm_id.setOnAction(this::load_program);

        tbl_payment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });

        initUI();

    }

    private void initUI() {
        clearFields();
        txt_payment_id.setDisable(true);
        cmb_student_id.setDisable(true);
        cmb_progrm_id.setDisable(true);
        txt_student_name.setDisable(true);
        txt_prgrm_name.setDisable(true);
        txt_amount.setDisable(true);
        txt_paid_amount.setDisable(true);
        txt_payment_id.setDisable(true);
        btn_updateBalance.setDisable(true);
        txt_pay_date.setDisable(true);
    }

    private void loadComboBoxes() throws Exception {
        List<Programs> programsList = programDAOImpl.getAll();
        for (Programs programs : programsList) {
            cmb_progrm_id.getItems().add(programs.getProgramID());
        }

        List<Students> studentsList = studentDAOImpl.getAll();
        for (Students students : studentsList) {
            cmb_student_id.getItems().add(String.valueOf(students.getStID()));
        }
    }

    private void setupTableColumns() {
        tbl_payment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        tbl_payment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tbl_payment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("programId"));
        tbl_payment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbl_payment.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("balance"));
        tbl_payment.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        tbl_payment.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
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

    private void populateFields(PaymentDTO payment) {
        txt_payment_id.setText(payment.getPaymentID());
        cmb_student_id.setValue(payment.getStudentId());
        cmb_progrm_id.setValue(payment.getProgramId());
        txt_pay_date.setValue(payment.getPaymentDate());
        txt_amount.setText(String.valueOf(payment.getAmount()));
        txt_paid_amount.setText(String.valueOf(payment.getPaidAmount()));
        btn_updateBalance.setText(String.valueOf(payment.getBalance()));

        txt_payment_id.setDisable(false);
        cmb_student_id.setDisable(false);
        cmb_progrm_id.setDisable(false);
        txt_student_name.setDisable(false);
        txt_prgrm_name.setDisable(false);
        txt_amount.setDisable(false);
        txt_paid_amount.setDisable(false);
        txt_payment_id.setDisable(false);
        btn_updateBalance.setDisable(false);
        txt_pay_date.setDisable(false);

        btn_save.setText("Update");
        btn_save.setDisable(false);
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
            double fullPaymentValue = Double.parseDouble(txt_amount.getText());
            double paidAmountValue = Double.parseDouble(txt_paid_amount.getText());

            double balance = fullPaymentValue - paidAmountValue;
            btn_updateBalance.setText(String.valueOf(balance));
        } catch (NumberFormatException e) {
            btn_updateBalance.setText("0.0");
        }
    }

    public void save(ActionEvent event) {

        String payId = txt_payment_id.getText();
        String stId = cmb_student_id.getValue();
        String prId = cmb_progrm_id.getValue();
        LocalDate date = txt_pay_date.getValue();
        Double amount = Double.parseDouble(txt_amount.getText());
        Double paidAmount = Double.parseDouble(txt_paid_amount.getText());
        Double balance = Double.parseDouble(btn_updateBalance.getText());

        double fee = 0.0;
        try {
            fee = Double.parseDouble(String.valueOf(paidAmount));
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid fee value. Please enter a valid number.").show();
            return;
        }

        if (btn_save.getText().equalsIgnoreCase("Save")) {
            try {
                if (existPayment(payId)) {
                    new Alert(Alert.AlertType.ERROR, payId + "already Exists!").show();
                    return;
                }

                paymentBO.addPayment(new PaymentDTO(payId, stId, prId, date, amount, paidAmount, balance));

                tbl_payment.getItems().add(new PaymentDTO(payId, stId, prId, date, amount, paidAmount, balance));

                new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the payment! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                if (!existPayment(payId)) {
                    new Alert(Alert.AlertType.ERROR, "Can't find the ID " + payId + "! Enter another one!").show();
                    return;
                }

                paymentBO.updatePayment(new PaymentDTO(payId, stId, prId, date, amount, paidAmount, balance));

                PaymentDTO selectedPayment = tbl_payment.getSelectionModel().getSelectedItem();
                selectedPayment.setStudentId(stId);
                selectedPayment.setProgramId(prId);
                selectedPayment.getPaymentDate();
                selectedPayment.setPaidAmount(amount);
                selectedPayment.setPaidAmount(paidAmount);
                selectedPayment.setBalance(balance);

                tbl_payment.refresh();

                txt_paid_amount.clear();

                new Alert(Alert.AlertType.CONFIRMATION, "Student updated successfully!").show();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the payment! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            btnAddNew.fire();

        }

//        try {
//            PaymentDTO payment = new PaymentDTO(
//                    txt_payment_id.getText(),
//                    cmb_student_id.getValue(),
//                    cmb_progrm_id.getValue(),
//                    txt_pay_date.getValue(),
//                    Double.parseDouble(txt_amount.getText()),
//                    Double.parseDouble(txt_paid_amount.getText()),
//                    Double.parseDouble(btn_updateBalance.getText())
//            );
//
//            paymentBO.addPayment(payment);
//            new Alert(Alert.AlertType.CONFIRMATION, "Payment added successfully").show();
//            loadAllPaymentsDetails();
//            clearFields();
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Error saving payment").show();
//        }
    }

    private boolean existPayment(String payId) throws Exception {
        return paymentBO.existPayment(payId);
    }

    private void clearFields() {
        txt_payment_id.clear();
        txt_pay_date.setValue(null);
        txt_amount.clear();
        txt_paid_amount.clear();
        btn_updateBalance.setText("Update Balance");
        txt_student_name.clear();
        txt_prgrm_name.clear();
        cmb_student_id.setValue(null);
        cmb_progrm_id.setValue(null);
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

    public void txtCustomerNAmeOnKeyReleased(KeyEvent keyEvent) {

    }

    public void txtCustomerContactOnKeyReleased(KeyEvent keyEvent) {

    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txt_payment_id.setDisable(false);
        cmb_student_id.setDisable(false);
        cmb_progrm_id.setDisable(false);
        txt_student_name.setDisable(false);
        txt_prgrm_name.setDisable(false);
        txt_amount.setDisable(false);
        txt_paid_amount.setDisable(false);
        txt_payment_id.setDisable(false);
        btn_updateBalance.setDisable(false);
        txt_pay_date.setDisable(false);

        txt_payment_id.clear();
        txt_payment_id.setText(generateNewId());
        cmb_student_id.setValue(null);
        cmb_progrm_id.setValue(null);
        txt_student_name.clear();
        txt_prgrm_name.clear();
        txt_amount.clear();
        txt_payment_id.clear();

        btn_updateBalance.setText("Update Balance");
        txt_pay_date.setValue(null);
        generatePaymentId();
    }

    private String generateNewId() {
        return null;
    }

    private String generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("P00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "P00" + ++idNum;
                txt_payment_id.setText(availableId);
                return availableId;
            } else {
                txt_payment_id.setText("P001");
                return "P001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getLastPaymentID() {
        List<PaymentDTO> tempPaymentList = new ArrayList<>(tbl_payment.getItems());

        if (tempPaymentList.isEmpty()) {
            return null;
        }

        tempPaymentList.sort(Comparator.comparing(PaymentDTO::getPaymentID));

        return tempPaymentList.get(tempPaymentList.size() - 1).getPaymentID();
    }

    public void search(ActionEvent actionEvent) {
        try {
            String paymentID = txtSearch.getText();
            PaymentDTO payments = paymentBO.searchPayment(paymentID);
            if (payments != null) {
                populateFields(payments);
            } else {
                new Alert(Alert.AlertType.WARNING, "Payment Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Searching Payment!").show();
        }
        txtSearch.clear();
    }
}



