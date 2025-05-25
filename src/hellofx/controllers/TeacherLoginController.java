package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class TeacherLoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void onLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Fake credentials
        String fakeEmail = "teacher@example.com";
        String fakePassword = "teacher123";

        if (email.equals(fakeEmail) && password.equals(fakePassword)) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 1024, 640));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email or password for teacher.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/welcome.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 640));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}