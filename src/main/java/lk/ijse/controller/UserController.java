package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserController {

    @FXML private TextField txt_id, txt_name, txt_email, txt_U_Name, txt_password, txt_Search;
    @FXML private Button btn_save, btn_update, btn_delete, btn_getAll, btn_search, btnAddNew;
    @FXML private TableView<UserDTO> tbl_users;
    @FXML private ComboBox<String> txt_position;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() throws Exception {
        tbl_users.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        tbl_users.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_users.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tbl_users.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tbl_users.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("password"));
        tbl_users.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("position"));

        txt_name.setOnAction(actionEvent -> txt_email.requestFocus());
        txt_email.setOnAction(actionEvent -> txt_U_Name.requestFocus());
        txt_U_Name.setOnAction(actionEvent -> txt_password.requestFocus());
        txt_password.setOnAction(actionEvent -> txt_position.requestFocus());

        loadAllUsers();

        ObservableList<String> positions = FXCollections.observableArrayList("Admin", "Coordinator");
        txt_position.setItems(positions);

        tbl_users.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) populateFields(newSelection);
        });

        initUI();

    }

    private void initUI() {
        clearFields();
        txt_id.setDisable(true);
        txt_name.setDisable(true);
        txt_email.setDisable(true);
        txt_U_Name.setDisable(true);
        txt_password.setDisable(true);
        txt_position.setDisable(true);
        btn_save.setDisable(false);
        btn_delete.setDisable(false);
    }

    private void populateFields(UserDTO user) {
        txt_id.setText(user.getUserID());
        txt_name.setText(user.getName());
        txt_email.setText(user.getEmail());
        txt_U_Name.setText(user.getUserName());
        txt_password.setText(user.getPassword());
        txt_position.setValue(user.getPosition());

        txt_id.setDisable(false);
        txt_name.setDisable(false);
        txt_email.setDisable(false);
        txt_U_Name.setDisable(false);
        txt_password.setDisable(false);
        txt_position.setDisable(false);

        btn_save.setText("Update");
        btn_save.setDisable(false);
    }

    public void save(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String uId = txt_id.getText();
        String name = txt_name.getText();
        String email = txt_email.getText();
        String userName = txt_U_Name.getText();
        String password = txt_password.getText();
        String position = txt_position.getValue();

        if (btn_save.getText().equalsIgnoreCase("Save")) {
            try {
                if (existUser(uId)) {
                    new Alert(Alert.AlertType.ERROR, uId + " already exists!").show();
                    return;
                }

                userBO.addUsers(new UserDTO(uId, name, email, userName, password, position));
                tbl_users.getItems().add(new UserDTO(uId, name, email, userName, password, position));

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the user! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            try {
                if (!existUser(uId)) {
                    new Alert(Alert.AlertType.ERROR, "Can't find the ID " + uId + "! Enter another one!").show();
                    return;
                }

                userBO.updateUser(new UserDTO(uId, name, email, userName, password, position));
                UserDTO selectedUser = tbl_users.getSelectionModel().getSelectedItem();
                selectedUser.setName(name);
                selectedUser.setEmail(email);
                selectedUser.setUserName(userName);
                selectedUser.setPassword(password);
                selectedUser.setPosition(position);
                tbl_users.refresh();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the user! " + e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        btnAddNew.fire();
    }

    private boolean existUser(String uId) throws Exception {
        return userBO.existUser(uId);
    }

    public void delete() throws Exception {
        try {
            if (userBO.deleteUser(txt_id.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully!").show();
                loadAllUsers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "User Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Deleting User").show();
        }
    }

    public void search() throws Exception {
        try {
            UserDTO userDTO = userBO.searchUser(txt_Search.getText());
            if (userDTO != null) {
                populateFields(userDTO);
            } else {
                new Alert(Alert.AlertType.WARNING, "User Not Found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Searching User").show();
        }
        txt_Search.clear();
    }

    public void getAll() throws Exception {
        loadAllUsers();
    }

    private void loadAllUsers() throws Exception {
        try {
            List<UserDTO> allUsers = userBO.getAllUsers();
            ObservableList<UserDTO> items = FXCollections.observableArrayList(allUsers);
            tbl_users.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Loading Users!").show();
        }
    }

    private void clearFields() {
        txt_id.clear();
        txt_name.clear();
        txt_email.clear();
        txt_U_Name.clear();
        txt_password.clear();
        txt_position.setValue(null);
    }

    public void txtCustomerNAmeOnKeyReleased(KeyEvent keyEvent) {

    }

    public void txtCustomerContactOnKeyReleased(KeyEvent keyEvent) {

    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txt_id.setDisable(false);
        txt_name.setDisable(false);
        txt_email.setDisable(false);
        txt_U_Name.setDisable(false);
        txt_password.setDisable(false);
        txt_position.setDisable(false);
        txt_id.clear();
        txt_id.setText(generateNewId());
        txt_name.clear();
        txt_email.clear();
        txt_U_Name.clear();
        txt_password.clear();
        txt_position.setValue(null);
        txt_name.requestFocus();
        btn_save.setDisable(false);
        btn_save.setText("Save");
        tbl_users.getSelectionModel().clearSelection();
    }

    private String generateNewId() {
        try {
            return userBO.generateNew_UserID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "failed to generate a new id!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tbl_users.getItems().isEmpty()) {
            return "U001";
        } else {
            String uId = getLastUserID();
            int newUserID = Integer.parseInt(uId.replace("U", "")) + 1;
            return String.format("U00-%03d", newUserID);
        }
    }

    private String getLastUserID() {
        List<UserDTO> tempUserList = new ArrayList<>(tbl_users.getItems());

        if (tempUserList.isEmpty()) {
            return null;
        }

        tempUserList.sort(Comparator.comparing(UserDTO::getUserID));

        return tempUserList.get(tempUserList.size() - 1).getUserID();
    }
}
