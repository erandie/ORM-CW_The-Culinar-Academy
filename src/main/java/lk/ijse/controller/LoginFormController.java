package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;

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

    @FXML
    private AnchorPane root;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

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

   /* *//*public void login(ActionEvent actionEvent) {
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
*/
   @FXML
   void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
       String name = txt_userName.getText();
       String password = txt_password.getText();

       boolean isAuthenticated = userBO.checkCredentials(name, password);

       if (isAuthenticated) {
           new Alert(Alert.AlertType.CONFIRMATION, "Login successful").show();
           navigateToTheDashboard(name, password);  // Pass name and password directly
       } else {
           new Alert(Alert.AlertType.ERROR, "Invalid userID or password").show();
       }
   }

    private void navigateToTheDashboard(String userName, String password) {
        // Use the already validated credentials here
        User user = validateUser(userName, password);  // No need to check credentials again

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

                // Close the current window
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
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/User-02.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void click(ActionEvent actionEvent) {
        System.out.println("Hyperlink clicked!");
    }
}
