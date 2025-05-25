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

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button editCourseBtn;
    @FXML
    private Button manageStudentsBtn;
    @FXML
    private Button attendanceBtn;
    @FXML
    private Button marksBtn;
    @FXML
    private Button reportBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadView("/hellofx/fxml/TeacherDashboardGrid.fxml"); // Show dashboard grid by default

        editCourseBtn.setOnAction(e -> loadView("/hellofx/fxml/TeacherCourseEditor.fxml"));
        manageStudentsBtn.setOnAction(e -> loadView("/hellofx/fxml/TeacherEnroll.fxml"));
        attendanceBtn.setOnAction(e -> loadView("/hellofx/fxml/TeacherAttendance.fxml"));
        marksBtn.setOnAction(e -> loadView("/hellofx/fxml/TeacherMarks.fxml"));
        reportBtn.setOnAction(e -> loadView("/hellofx/fxml/TeacherFinal.fxml"));
    }

    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}