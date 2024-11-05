package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.dto.UserDTO;

public class UserController {

    @FXML private TextField txt_id, txt_name, txt_email, txt_U_Name, txt_password, txt_position;
    @FXML private Button btn_save, btn_update, btn_delete, btn_getAll, btn_search;
    @FXML private TableView<UserDTO> tbl_users;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() throws Exception {
        tbl_users.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        tbl_users.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_users.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tbl_users.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tbl_users.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("password"));
        tbl_users.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("position"));

        loadAllUsers();

        tbl_users.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) populateFields(newSelection);
        });
    }

    private void populateFields(UserDTO user) {
        txt_id.setText(String.valueOf(user.getUserID()));
        txt_name.setText(user.getName());
        txt_email.setText(user.getEmail());
        txt_U_Name.setText(user.getUserName());
        txt_password.setText(user.getPassword());
        txt_position.setText(user.getPosition());
    }

    public void save() throws Exception {
        UserDTO user = new UserDTO(
                null,
                txt_name.getText(),
                txt_email.getText(),
                txt_U_Name.getText(),
                txt_password.getText(),
                txt_position.getText());
        if (userBO.addUsers(user)) clearFields();
        loadAllUsers();
    }

    public void update() throws Exception {
        UserDTO user = new UserDTO(
                Long.parseLong(txt_id.getText()),
                txt_name.getText(),
                txt_email.getText(),
                txt_U_Name.getText(),
                txt_password.getText(),
                txt_position.getText());
        if (userBO.updateUser(user)) clearFields();
        loadAllUsers();
    }

    public void delete() throws Exception {
        Long userID = Long.parseLong(txt_id.getText());
        if (userBO.deleteUser(String.valueOf(userID))) clearFields();
        loadAllUsers();
    }

    public void search() throws Exception {
        UserDTO user = userBO.searchUser(String.valueOf(Long.parseLong(txt_id.getText())));
        if (user != null) populateFields(user);
    }

    public void getAll() throws Exception {
        loadAllUsers();
    }

    private void loadAllUsers() throws Exception {
        ObservableList<UserDTO> userList = FXCollections.observableArrayList(userBO.getAllUsers());
        tbl_users.setItems(userList);
    }

    private void clearFields() {
        txt_id.clear();
        txt_name.clear();
        txt_email.clear();
        txt_U_Name.clear();
        txt_password.clear();
        txt_position.clear();
    }
}
