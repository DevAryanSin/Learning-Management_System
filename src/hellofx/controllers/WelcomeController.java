package hellofx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class WelcomeController {

    @FXML
    private void onStudentLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/student_login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 400, 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onTeacherLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/teacher_login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 400, 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}