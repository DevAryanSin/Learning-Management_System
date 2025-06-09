package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachersController implements Initializable {
    @FXML private AnchorPane mainPane;
    @FXML private Button editCourseBtn;
    @FXML private Button manageStudentsBtn;
    @FXML private Button attendanceBtn;
    @FXML private Button marksBtn;
    @FXML private Button reportBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editCourseBtn.setOnAction(e -> loadView("TeacherCourseEditor.fxml"));
        manageStudentsBtn.setOnAction(e -> loadView("TeacherEnroll.fxml"));
        attendanceBtn.setOnAction(e -> loadView("TeacherAttendance.fxml"));
        marksBtn.setOnAction(e -> loadView("TeacherMarks.fxml"));
        reportBtn.setOnAction(e -> loadView("TeacherFinal.fxml"));
    }

    private void loadView(String fxmlName) {
        try {
            URL fxmlUrl = getClass().getResource("/hellofx/fxml/" + fxmlName);
            if (fxmlUrl == null) {
                System.err.println("Cannot find FXML file: " + fxmlName);
                return;
            }
            
            Parent view = FXMLLoader.load(fxmlUrl);
            mainPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading " + fxmlName + ": " + e.getMessage());
        }
    }
}