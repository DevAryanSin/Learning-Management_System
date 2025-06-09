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
    // In TeacherLoginController.java and StudentLoginController.java
    @FXML
    private void onForgotPassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hellofx/fxml/forgot_password.fxml"));
            Parent view = loader.load();
            
            // Get controller and set source
            ForgotPasswordController controller = loader.getController();
            controller.setSourceView("teacher");
            
            Stage stage = (Stage) emailField.getScene().getWindow();
            Scene scene = new Scene(view, 1024, 640);
            scene.getRoot().setStyle("-fx-background-color: #FFFFFF;");
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Navigation Error", "Could not open password reset screen");
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
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