package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;

    @FXML
    private Hyperlink hy_click;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_userName;

    public User validateUser(String userName, String password) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE userName = :userName AND password = :password", User.class);
            query.setParameter("userName", userName);
            query.setParameter("password", password);

            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println("Error during user validation: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void login(ActionEvent actionEvent) {
        String name = txt_userName.getText();
        String password = txt_password.getText();

        User user = validateUser(name, password);

        if (user != null) {
            try {
                String fxmlFile = user.getPosition().equals("Admin")
                        ? "/view/Dashboard.fxml"
                        : "/view/Dashboard-02.fxml";

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                stage.show();

                ((Stage) btn_login.getScene().getWindow()).close();
            } catch (IOException e) {
                System.err.println("Error loading dashboard: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid Username or Password");
        }
    }

    public void register(ActionEvent actionEvent) {
        System.out.println("Register button clicked!");
    }

    public void click(ActionEvent actionEvent) {
        System.out.println("Hyperlink clicked!");
    }
}
